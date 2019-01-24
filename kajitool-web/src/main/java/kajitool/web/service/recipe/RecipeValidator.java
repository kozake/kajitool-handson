package kajitool.web.service.recipe;

import am.ik.yavi.core.ConstraintGroup;
import am.ik.yavi.core.ConstraintViolation;
import am.ik.yavi.core.ConstraintViolations;
import am.ik.yavi.core.Validator;
import kajitool.web.domain.model.Recipe;
import kajitool.web.domain.model.RecipeDetail;
import kajitool.web.service.common.ServiceException;
import kajitool.web.service.common.ServiceMessage;

import java.util.stream.Collectors;

public final class RecipeValidator {

    private enum Group implements ConstraintGroup {
        CREATE, UPDATE
    }

    private static final Validator<RecipeDetail> recipeDetailValidator
            = Validator.<RecipeDetail>builder()
            .constraint(RecipeDetail::getQuantity, "validator", c -> c.greaterThan(0))
            .constraintOnCondition(Group.CREATE.toCondition(), b ->
                    b.constraint(RecipeDetail::getId, "id", c -> c.isNull()))
            .constraintOnCondition(Group.UPDATE.toCondition(), b ->
                    b.constraint(RecipeDetail::getId, "id", c -> c.notNull()))
            .build();

    private static final Validator<Recipe> recipeValidator
            = Validator.<Recipe>builder()
            .constraint(Recipe::getName, "name", c -> c.notBlank()
                    .lessThanOrEqual(100))
            .constraint(Recipe::getRecipeDetails, "recipeDetails", c -> c.notNull()
                    .greaterThan(0))
            .forEach(Recipe::getRecipeDetails, "recipeDetails", recipeDetailValidator)
            .constraintOnCondition(Group.CREATE.toCondition(), b ->
                    b.constraint(Recipe::getId, "id", c -> c.isNull()))
            .constraintOnCondition(Group.UPDATE.toCondition(), b ->
                    b.constraint(Recipe::getId, "id", c -> c.notNull()))
            .build();

    public static void validateOnCreate(final Recipe recipe) {
        recipeValidator.validate(recipe, Group.CREATE)
                .throwIfInvalid(RecipeValidator::toServiceException);
    }

    public static void validateOnUpdate(final Recipe recipe) {
        recipeValidator.validate(recipe, Group.UPDATE)
                .throwIfInvalid(RecipeValidator::toServiceException);
    }

    private static ServiceException toServiceException(
            final ConstraintViolations v) {
        ServiceMessage msg = new ServiceMessage(
                "validation",
                "validation faild.",
                v.violations()
                        .stream()
                        .map(ConstraintViolation::message)
                        .collect(Collectors.toList()));
        return new ServiceException(msg);
    }
}
