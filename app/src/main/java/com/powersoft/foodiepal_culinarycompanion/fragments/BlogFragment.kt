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
import com.powersoft.foodiepal_culinarycompanion.activities.AddBlogActivity
import com.powersoft.foodiepal_culinarycompanion.adapters.BlogAdapter
import com.powersoft.foodiepal_culinarycompanion.databinding.FragmentBlogBinding
import com.powersoft.foodiepal_culinarycompanion.models.BlogPost

class BlogFragment : Fragment() {

    private lateinit var b: FragmentBlogBinding
    private lateinit var blogAdapter: BlogAdapter
    private var blogList = mutableListOf<BlogPost>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        b = FragmentBlogBinding.bind(inflater.inflate(R.layout.fragment_blog, container, false))
        return b.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        generateSampleBlogPost()
        blogAdapter = BlogAdapter(blogList)
        b.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        b.recyclerView.adapter = blogAdapter

        val contract =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
                if (it.resultCode == AppCompatActivity.RESULT_OK) {
                    val blogPost = it.data?.getParcelableExtra("blogPost") as? BlogPost
                    if (blogPost != null) {
                        blogList.add(blogPost)
                    }
                    blogAdapter.notifyItemChanged(blogList.size)
                } else if (it.resultCode == AppCompatActivity.RESULT_CANCELED) {
                    Toast.makeText(requireActivity(), "Cancelled by User", Toast.LENGTH_SHORT)
                        .show()
                }
            }

        b.fabAdd.setOnClickListener {
            contract.launch(Intent(requireActivity(), AddBlogActivity::class.java))
        }
    }

    private fun generateSampleBlogPost() {
        blogList.add(
            BlogPost(
                "Serious Eats",
                "Serious Eats is a blog focused on delivering the best food recipes, techniques, and guides. They believe in rigorous testing to understand the science of cooking."
            )
        )
        blogList.add(
            BlogPost(
                "Smitten Kitchen",
                "Smitten Kitchen is a food blog providing comfort food recipes with a twist. The blog is known for its detailed step-by-step cooking guides and beautiful food photography."
            )
        )
        blogList.add(
            BlogPost(
                "Minimalist Baker",
                "Minimalist Baker is a food blog that shares plant-based recipes requiring 10 ingredients or less, 1 bowl, or 30 minutes or less to prepare. The blog is all about making food simple and approachable."
            )
        )
    }
}