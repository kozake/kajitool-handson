package kajitool.web.view.mapper;

import kajitool.web.view.model.RecipeListView;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface RecipeListViewMapper {
    @Select(" SELECT " +
            "   ID as id, " +
            "   NAME as name, " +
            "   select count(*) from RECIPE_DETAIL as D where D.RECIPE_ID = RECIPE.ID as materialCount " +
            " from RECIPE ")
    List<RecipeListView> selectAll();
}
