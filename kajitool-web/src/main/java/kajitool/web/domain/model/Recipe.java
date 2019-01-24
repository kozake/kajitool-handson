package kajitool.web.domain.model;

import java.util.Date;
import java.util.List;

public class Recipe {
    private Long id;
    private String name;
    private int version;
    private Date updatedAt;
    private List<RecipeDetail> recipeDetails;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public List<RecipeDetail> getRecipeDetails() {
        return recipeDetails;
    }

    public void setRecipeDetails(List<RecipeDetail> recipeDetails) {
        this.recipeDetails = recipeDetails;
    }
}
