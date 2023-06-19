package fastcampus.part2.chapter11

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import fastcampus.part2.chapter11.databinding.FragmentOrderBinding
import kotlin.math.abs

class LookFragmentFragment : Fragment(R.layout.fragment_look) {

    private lateinit var binding: FragmentOrderBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentOrderBinding.bind(view)

        val menuData = context?.readData("menu.json", Menu::class.java) ?: return
        val menuAdapter = MenuAdapter().apply {
            submitList(menuData.coffee)
        }

        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = menuAdapter
        }

        binding.appbarLayout.addOnOffsetChangedListener { appBarLayout, verticalOffset ->

            val seekPosition = abs(verticalOffset) / appBarLayout.totalScrollRange.toFloat()
            binding.motionLayout.progress = seekPosition
        }
    }
}
