package com.powersoft.foodiepal_culinarycompanion.adapters

import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filterable
import androidx.recyclerview.widget.RecyclerView
import com.powersoft.foodiepal_culinarycompanion.activities.RecipeDetailActivity
import com.powersoft.foodiepal_culinarycompanion.databinding.ItemRecipeBinding
import com.powersoft.foodiepal_culinarycompanion.models.Recipe
import android.widget.Filter

class RecipeAdapter(private var recipes: List<Recipe>) :
    RecyclerView.Adapter<RecipeAdapter.RecipeViewHolder>(), Filterable {

    private var recipeListFull: List<Recipe> = ArrayList(recipes)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeViewHolder {
        val binding =
            ItemRecipeBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return RecipeViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecipeViewHolder, position: Int) {
        holder.bind(recipes[position])
        holder.itemView.setOnClickListener {
            val context = holder.itemView.context
            val intent = Intent(context, RecipeDetailActivity::class.java)
            intent.putExtra("recipe", recipes[position])
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return recipes.size
    }

    class RecipeViewHolder(private val binding: ItemRecipeBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(recipe: Recipe) {
            binding.foodName.text = recipe.foodName
            binding.tvTime.text = recipe.foodDuration
            if (recipe.imagePath == null) {
                binding.imgFood.setImageResource(recipe.image)
            }else{
                binding.imgFood.setImageURI(Uri.parse(recipe.imagePath))
            }
            binding.ratingBar.rating = recipe.userRating
            binding.tvRating.text = recipe.userRating.toString()
        }
    }

    override fun getFilter(): Filter {
        return recipeFilter
    }

    private val recipeFilter: Filter = object : Filter() {
        override fun performFiltering(constraint: CharSequence?): FilterResults {
            val filteredList: MutableList<Recipe> = ArrayList()
            if (constraint == null || constraint.isEmpty()) {
                filteredList.addAll(recipeListFull)
            } else {
                val filterPattern = constraint.toString().toLowerCase().trim { it <= ' ' }
                for (item in recipeListFull) {
                    if (item.foodName.toLowerCase().contains(filterPattern)) {
                        filteredList.add(item)
                    }
                }
            }
            val results = FilterResults()
            results.values = filteredList
            return results
        }

        override fun publishResults(constraint: CharSequence?, results: FilterResults) {
            recipes = results.values as List<Recipe>
            notifyDataSetChanged()
        }
    }
}
