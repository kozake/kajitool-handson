package kajitool.web.service.recipe;

import kajitool.web.domain.model.Recipe;
import kajitool.web.domain.repository.RecipeRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class RecipeService {
    private final RecipeRepository recipeRepository;

    public RecipeService(RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }
    public Recipe create(final Recipe recipe) {
        RecipeValidator.validateOnCreate(recipe);
        return recipeRepository.create(recipe);
    }
    public Optional<Recipe> findById(final long id) {
        return recipeRepository.selectById(id);
    }
    public Recipe save(final Recipe recipe) {
        RecipeValidator.validateOnUpdate(recipe);
        return recipeRepository.update(recipe);
    }
    public void remove(final long id, final int version) {
        recipeRepository.remove(id, version);
    }
}
