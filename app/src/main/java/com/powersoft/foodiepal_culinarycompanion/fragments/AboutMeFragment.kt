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
import com.powersoft.foodiepal_culinarycompanion.activities.AddAboutActivity
import com.powersoft.foodiepal_culinarycompanion.adapters.AboutAdapter
import com.powersoft.foodiepal_culinarycompanion.databinding.FragmentAboutBinding
import com.powersoft.foodiepal_culinarycompanion.models.About

class AboutMeFragment : Fragment() {

    private lateinit var b: FragmentAboutBinding
    private lateinit var adapter: AboutAdapter
    private var aboutList = mutableListOf<About>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        b = FragmentAboutBinding.inflate(inflater, container, false)
        return b.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        generateDummyAbout()
        adapter = AboutAdapter(aboutList)

        b.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        b.recyclerView.adapter = adapter

        val contract =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
                if (it.resultCode == AppCompatActivity.RESULT_OK) {
                    val about = it.data?.getParcelableExtra("about") as? About
                    if (about != null) {
                        aboutList.add(about)
                    }
                    adapter.notifyItemChanged(aboutList.size)
                } else if (it.resultCode == AppCompatActivity.RESULT_CANCELED) {
                    Toast.makeText(requireActivity(), "Cancelled by User", Toast.LENGTH_SHORT)
                        .show()
                }
            }

        b.fabAddDetails.setOnClickListener {
            contract.launch(Intent(requireActivity(), AddAboutActivity::class.java))
        }
    }

    fun generateDummyAbout() {
        aboutList.add(
            About(
                "Ever since I discovered Korean cuisine, my culinary journey has been an exciting adventure. " +
                        "The vibrant flavors, the unique combination of ingredients, and the culture embedded in each dish have truly captivated my palate. " +
                        "From the spicy, fermented goodness of Kimchi, the comforting warmth of Samgyetang, " +
                        "to the sizzling delight of Samgyeopsal, each dish tells a story of Korea's rich culinary history. " +
                        "The communal dining culture, where dishes are shared, and meals are a time for conversation and connection, " +
                        "resonates deeply with me. My love for Korean food has not only enriched my culinary experiences but also inspired me to " +
                        "explore and appreciate other cuisines. " +
                        "This journey is just the beginning, and I look forward to discovering more culinary treasures in Korean cuisine.",
                "My favorite recipe is Bibimbap, a classic Korean dish. " +
                        "The name 'Bibimbap' literally means 'mixed rice'. " +
                        "At its core, Bibimbap is a hearty bowl of rice topped with an array of colorful" +
                        " and flavorful sautéed vegetables, a dollop of gochujang (Korean chili paste), and " +
                        "usually a bit of seasoned raw or grilled beef. A fried egg crowns the dish. The magic " +
                        "happens when you stir everything together. The yolk of the egg creates a rich sauce for " +
                        "the rice and the gochujang transforms from a thick paste to a light, spicy sauce that coats " +
                        "every grain of rice. The vegetables maintain a slight crunch, providing a nice contrast to the softness of the egg and rice. " +
                        "Each bite is a little different from the last, but every bite is absolutely delicious. Making Bibimbap at home has become a " +
                        "joy and a journey. It's a dish that's very forgiving and customizable. " +
                        "I love that I can switch out the vegetables based on what's in season, or adjust the amount of gochujang based on how spicy I want it. " +
                        "It's a dish that truly embraces the spirit of Korean cuisine - harmony and balance.",
                "My food philosophy is simple: eat real, whole foods." +
                        " I believe in consuming a variety of foods that are as close to their natural state as possible." +
                        " This means lots of fresh fruits and vegetables, whole grains, lean proteins, and healthy fats. " +
                        "I avoid processed foods and artificial ingredients whenever possible. I believe that food is not just fuel for our bodies, " +
                        "but also a source of pleasure and a way to connect with others and with the world around us. I believe in the power of cooking " +
                        "at home, not only because it allows us to control what goes into our bodies, but also because it allows us to share the joy " +
                        "of creating something delicious with our loved ones. I believe that everyone can learn to cook and that cooking can be a source of creativity and joy. " +
                        "I believe in the power of food to bring people together, to create memories, and to nourish not just our bodies, but also our souls."
            )
        )
    }
}