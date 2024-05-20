package com.powersoft.foodiepal_culinarycompanion.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.powersoft.foodiepal_culinarycompanion.R
import com.powersoft.foodiepal_culinarycompanion.activities.AddRecipeActivity
import com.powersoft.foodiepal_culinarycompanion.adapters.RecipeAdapter
import com.powersoft.foodiepal_culinarycompanion.databinding.FragmentRecipesBinding
import com.powersoft.foodiepal_culinarycompanion.models.Recipe

class RecipesFragment : Fragment() {

    private lateinit var b: FragmentRecipesBinding
    private lateinit var adapter: RecipeAdapter
    private var recipeList = mutableListOf<Recipe>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        b = FragmentRecipesBinding.inflate(inflater, container, false)
        return b.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        generateSampleRecipe()
        adapter = RecipeAdapter(recipeList)

        b.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        b.recyclerView.adapter = adapter

        val contract =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
                if (it.resultCode == AppCompatActivity.RESULT_OK) {
                    val recipe = it.data?.getParcelableExtra("recipe") as? Recipe
                    if (recipe != null) {
                        recipeList.add(recipe)
                    }
                    adapter.notifyItemChanged(recipeList.size)
                } else if (it.resultCode == AppCompatActivity.RESULT_CANCELED) {
                    Toast.makeText(requireActivity(), "Cancelled by User", Toast.LENGTH_SHORT)
                        .show()
                }
            }

        b.fabAdd.setOnClickListener {
            contract.launch(Intent(requireActivity(), AddRecipeActivity::class.java))
        }

    }

    private fun generateSampleRecipe() {
        recipeList.add(
            Recipe(
                "Vegetable Noodle",
                "12 Mins",
                R.drawable.ic_noodle,
                4.5f,
                null,
                "Vegetable noodles are a delicious and healthy alternative to traditional wheat-based noodles. Typically made from vegetables like zucchini, carrots, or sweet potatoes, they offer a nutritious option for those looking to cut down on carbohydrates or incorporate more veggies into their diet.",
                listOf(
                    "Noodles",
                    "Carrots",
                    "Bell peppers",
                    "Broccoli",
                    "Soy sauce",
                    "Garlic",
                    "Ginger",
                    "Green onions",
                    "Sesame oil",
                    "Salt"
                )
            )
        )
        recipeList.add(
            Recipe(
                "Spaghetti", "30 Mins", R.drawable.ic_spaghetti, 4.8f, null,
                "Spaghetti is a classic Italian pasta known for its long, thin cylindrical shape. Made from durum wheat semolina, spaghetti is one of the most popular and widely consumed pasta varieties worldwide.",
                listOf(
                    "Spaghetti pasta",
                    "Olive oil",
                    "Garlic",
                    "Tomato sauce",
                    "Ground buffalo",
                    "Onion",
                    "Parmesan cheese",
                    "Basil",
                    "Oregano",
                    "Salt",
                    "Black pepper"
                )
            )
        )
        recipeList.add(
            Recipe(
                "Chicken Spaghetti", "32 Mins", R.drawable.ic_chicken_spaghetti, 5f, null,
                "Chicken spaghetti is a comforting and flavorful dish that combines tender chicken with cooked spaghetti noodles, all coated in a rich and creamy sauce. This dish is a popular comfort food in many households, offering a satisfying and hearty meal that's perfect for both family dinners and gatherings with friends.",
                listOf(
                    "Spaghetti pasta",
                    "Olive oil",
                    "Garlic",
                    "Tomato sauce",
                    "Ground chicken",
                    "Onion",
                    "Parmesan cheese",
                    "Basil",
                    "Oregano",
                    "Salt",
                    "Black pepper"
                )
            )
        )
        recipeList.add(
            Recipe(
                "Pasta", "15 Mins", R.drawable.ic_pasta, 4.3f, null,
                "Pasta is a staple food in Italian cuisine and has become popular worldwide for its versatility, ease of preparation, and delicious taste. It is typically made from durum wheat semolina or a combination of semolina and other grains, although gluten-free varieties made from ingredients like rice flour or chickpea flour are also available.",
                listOf(
                    "Pasta",
                    "Olive oil",
                    "Garlic",
                    "Cherry tomatoes",
                    "Parmesan cheese",
                    "Basil",
                    "Salt",
                    "Black pepper",
                    "Red pepper flakes",
                    "Spinach"
                )
            )
        )
    }
}