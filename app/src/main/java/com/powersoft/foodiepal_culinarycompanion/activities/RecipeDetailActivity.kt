package com.powersoft.foodiepal_culinarycompanion.activities

import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.powersoft.foodiepal_culinarycompanion.databinding.ActivityRecipeDetailBinding
import com.powersoft.foodiepal_culinarycompanion.models.Recipe

class RecipeDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRecipeDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRecipeDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val recipe = intent.getParcelableExtra<Recipe>("recipe")

        binding.imgBack.setOnClickListener { super.onBackPressed() }

        binding.foodName.text = recipe?.foodName
        binding.tvTime.text = recipe?.foodDuration
        binding.foodDescription.text = recipe?.description
        if (recipe?.imagePath == null) {
            if (recipe != null) {
                binding.imgFood.setImageResource(recipe.image)
            }
        }else{
            binding.imgFood.setImageURI(Uri.parse(recipe.imagePath))
        }
        if (recipe != null) {
            binding.ratingBar.rating = recipe.userRating
        }
        if (recipe != null) {
            binding.tvRating.text = recipe.userRating.toString()
        }
        val ingredientsList = recipe?.ingredients
        val ingredientsText = ingredientsList?.mapIndexed { index, ingredient ->
            "${index + 1}. $ingredient"
        }?.joinToString(separator = ", ")
        binding.tvIngredients.text = "Ingredients:\n \n$ingredientsText"
    }
}
