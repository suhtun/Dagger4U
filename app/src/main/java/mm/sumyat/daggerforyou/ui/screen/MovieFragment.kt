package mm.sumyat.daggerforyou.ui.screen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingComponent
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import mm.sumyat.daggerforyou.R
import mm.sumyat.daggerforyou.binding.FragmentDataBindingComponent
import mm.sumyat.daggerforyou.databinding.FragmentMovieBinding
import mm.sumyat.daggerforyou.ui.listener.RetryCallback
import mm.sumyat.daggerforyou.util.AppExecutors
import mm.sumyat.daggerforyou.util.autoCleared
import javax.inject.Inject

@AndroidEntryPoint
class MovieFragment : Fragment() {

    val viewmodel by viewModels<MovieViewModel>()

    @Inject
    lateinit var appExecutors: AppExecutors

    var dataBindingComponent: DataBindingComponent = FragmentDataBindingComponent(this)

    var binding by autoCleared<FragmentMovieBinding>()

    var adapter by autoCleared<MovieAdapter>()

    private var loading: Boolean = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_movie,
            container,
            false,
            dataBindingComponent
        )

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.lifecycleOwner = viewLifecycleOwner
        initRecyclerView()
        val rvAdapter = MovieAdapter(
            dataBindingComponent = dataBindingComponent,
            appExecutors = appExecutors
        ) { }

        val gridLayoutManager = GridLayoutManager(context, 3)
        binding.repoList.layoutManager = gridLayoutManager
        binding.repoList.adapter = rvAdapter
        this.adapter = rvAdapter

        binding.callback = object : RetryCallback {
            override fun retry() {
                viewmodel.refreshCall()
            }
        }
    }

    private fun initRecyclerView() {
        binding.searchResult = viewmodel.results
        viewmodel.results.observe(viewLifecycleOwner, Observer { result ->
            loading = true
            if (result.data != null)
                adapter.submitList(result?.data)
        })
    }
}