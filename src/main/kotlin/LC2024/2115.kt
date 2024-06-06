package LC2024


class `2115` {
    fun findAllRecipes(recipes: Array<String>, ingredients: List<List<String>>, supplies: Array<String>): List<String> {
        val suppliesSet = supplies.toMutableSet()
        val answer = mutableListOf<String>()
        val recipesToIngredients = recipes.mapIndexed { index, s -> s to ingredients[index].toMutableSet() }.toMutableSet()

        while (recipesToIngredients.isNotEmpty()) {
            val toRemove = recipesToIngredients.mapIndexedNotNull { index, (recipe, ingredients) ->
                if (suppliesSet.containsAll(ingredients)) {
                    suppliesSet.add(recipe)
                    answer.add(recipe)
                    recipe
                } else null
            }.toSet()
            if (toRemove.isEmpty()) return answer
            else recipesToIngredients.removeAll { it.first in toRemove  }
        }

        return answer
    }


}

fun main() {
    val soln = `2115`()
    println(soln.findAllRecipes(
        arrayOf("bread","sandwich"),
        listOf(
            listOf("yeast","flour"),
            listOf("bread", "meat"),
        ),
        arrayOf("yeast","flour","meat")
    ))

}