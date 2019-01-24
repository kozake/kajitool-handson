package kajitool.web.domain.repository;

import kajitool.dao.mapper.RecipeDetailEntityMapper;
import kajitool.dao.mapper.RecipeEntityMapper;
import kajitool.dao.mapper.SequenceMapper;
import kajitool.dao.model.RecipeDetailEntity;
import kajitool.dao.model.RecipeDetailEntityExample;
import kajitool.dao.model.RecipeEntity;
import kajitool.dao.model.RecipeEntityExample;
import kajitool.web.domain.model.Recipe;
import kajitool.web.domain.model.RecipeDetail;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;
import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class RecipeRepository {

    @Mapper
    public interface RecipeMap {
        RecipeMap INSTANCE = Mappers.getMapper(RecipeMap.class);

        RecipeEntity toEntity(Recipe recipe);

        @Mappings({
                @Mapping(target = "recipeDetails", ignore = true)
        })
        Recipe toModel(RecipeEntity recipeEntity);
    }

    @Mapper
    public interface RecipeDetailMap {
        RecipeDetailMap INSTANCE = Mappers.getMapper(RecipeDetailMap.class);

        RecipeDetailEntity toEntity(RecipeDetail recipeDetail);

        RecipeDetail toModel(RecipeDetailEntity recipeDetailEntity);
    }

    private final RecipeEntityMapper recipeMapper;
    private final RecipeDetailEntityMapper recipeDetailMapper;
    private final SequenceMapper sequenceMapper;

    public RecipeRepository(
            final RecipeEntityMapper recipeMapper,
            final RecipeDetailEntityMapper recipeDetailMapper,
            final SequenceMapper sequenceMapper) {
        this.recipeMapper = recipeMapper;
        this.recipeDetailMapper = recipeDetailMapper;
        this.sequenceMapper = sequenceMapper;
    }

    public Optional<Recipe> selectById(long id) {
        Optional<Recipe> optionalRecipe = Optional.ofNullable(
                recipeMapper.selectByPrimaryKey(id))
                .map(RecipeMap.INSTANCE::toModel);

        optionalRecipe.ifPresent(recipe -> {
            List<RecipeDetail> recipeDetails =
                    recipeDetailMapper.selectByExample(
                            new RecipeDetailEntityExample() {{
                                createCriteria().andRecipeIdEqualTo(id);
                            }})
                            .stream()
                            .map(RecipeDetailMap.INSTANCE::toModel)
                            .collect(Collectors.toList());
            recipe.setRecipeDetails(recipeDetails);
        });
        return optionalRecipe;
    }

    public Recipe create(final Recipe recipe) {
        if (recipe.getId() == null) {
            recipe.setId(sequenceMapper.nextval("RECIPE__ID_SEQ"));
        }
        recipe.setVersion(recipe.getVersion() + 1);
        recipe.setUpdatedAt(new Date());
        recipeMapper.insertSelective(RecipeMap.INSTANCE.toEntity(recipe));
        recipe.getRecipeDetails().forEach(detail -> {
            if (detail.getId() == null) {
                detail.setId(sequenceMapper.nextval("RECIPE_DETAIL__ID_SEQ"));
            }
            detail.setRecipeId(recipe.getId());
            recipeDetailMapper.insertSelective(RecipeDetailMap.INSTANCE.toEntity(detail));
        });
        return recipe;
    }

    public Recipe update(final Recipe recipe) {
        remove(recipe.getId(), recipe.getVersion());
        return create(recipe);
    }

    public void remove(final long id, final int version) {
        if (recipeMapper.selectByPrimaryKey(id) == null) {
            return;
        }
        recipeDetailMapper.deleteByExample(new RecipeDetailEntityExample() {{
            createCriteria()
                    .andRecipeIdEqualTo(id);
        }});
        int count = recipeMapper.deleteByExample(new RecipeEntityExample() {{
            createCriteria()
                    .andIdEqualTo(id)
                    .andVersionEqualTo(version);
        }});
        // 排他チェック
        if (count == 0) {
            throw new OptimisticLockingFailureException(
                    String.format("recipe id = [%d]", id));
        }
    }
}
