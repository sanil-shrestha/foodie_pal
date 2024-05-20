package com.powersoft.foodiepal_culinarycompanion.activities

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.powersoft.foodiepal_culinarycompanion.databinding.ActivityAddRecipeBinding
import com.powersoft.foodiepal_culinarycompanion.models.Recipe

class AddRecipeActivity : AppCompatActivity() {

    private lateinit var b: ActivityAddRecipeBinding
    private val MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE = 1
    private var photoUrl: Uri? = null
    private val pickMedia =
        registerForActivityResult(ActivityResultContracts.PickVisualMedia()) { uri ->
            if (uri != null) {
                photoUrl = uri
                Glide.with(this)
                    .load(photoUrl)
                    .into(b.imgRecipe)
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        b = ActivityAddRecipeBinding.inflate(layoutInflater)
        setContentView(b.root)

        b.imgBack.setOnClickListener { super.onBackPressed() }

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE)
            !== PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE),
                MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE
            )
        }

        b.imgRecipe.setOnClickListener {
            pickMedia.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly))
        }

        b.btnAddRecipe.setOnClickListener {
            if (!validate()) return@setOnClickListener

            val recipeName = b.etRecipeName.text.toString()
            val time = b.etTime.text.toString()
            val rating = b.ratingBar.rating
            val description = b.foodDescription.text.toString()
            val ingredientsList = b.ingredients.text.toString().split(",").map { it.trim() }

            val recipe = Recipe(recipeName, time, 0, rating, photoUrl.toString(), description, ingredientsList)

            val intent = Intent()
            intent.putExtra("recipe", recipe)
            setResult(RESULT_OK, intent)
            finish()
        }
    }

    private fun validate(): Boolean {
        if (photoUrl == null) {
            Toast.makeText(this, "Recipe image is required!!", Toast.LENGTH_SHORT).show()
            return false
        } else if (b.etRecipeName.text.isEmpty()) {
            b.etRecipeName.error = "Recipe Name is required!!"
            b.etRecipeName.requestFocus()
            return false
        } else if (b.etTime.text.isEmpty()) {
            b.etTime.error = "Time is required!!"
            b.etTime.requestFocus()
            return false
        } else if(b.ingredients.text.isEmpty()){
            b.ingredients.error = "Ingredients are required!!"
            b.ingredients.requestFocus()
            return false
        } else if (b.foodDescription.text.isEmpty()) {
            b.foodDescription.error = "Description is required!!"
            b.foodDescription.requestFocus()
            return false
        }
        return true
    }
}