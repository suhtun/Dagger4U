package mm.sumyat.daggerforyou.ui.screen

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingComponent
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import mm.sumyat.daggerforyou.R
import mm.sumyat.daggerforyou.databinding.MovieItemLayoutBinding
import mm.sumyat.daggerforyou.storage.Movie
import mm.sumyat.daggerforyou.ui.paging.DataBoundListAdapter
import mm.sumyat.daggerforyou.util.AppExecutors

class  MovieAdapter(
    private val dataBindingComponent: DataBindingComponent,
    appExecutors: AppExecutors,
    private val repoClickCallback: ((Movie) -> Unit)?
) : DataBoundListAdapter<Movie, MovieItemLayoutBinding>(
    appExecutors = appExecutors,
    diffCallback = object : DiffUtil.ItemCallback<Movie>() {
        override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean {
            return oldItem.id == newItem.id && oldItem.title == newItem.title
        }

        override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean {
            return oldItem.overview == newItem.overview
                    && oldItem.release_date == newItem.release_date
        }
    }
) {

    override fun createBinding(parent: ViewGroup): MovieItemLayoutBinding {
        val binding = DataBindingUtil.inflate<MovieItemLayoutBinding>(
            LayoutInflater.from(parent.context),
            R.layout.movie_item_layout,
            parent,
            false,
            dataBindingComponent
        )
        binding.root.setOnClickListener {
            binding.movie?.let {
                repoClickCallback?.invoke(it)
            }
        }
        return binding
    }

    override fun bind(binding: MovieItemLayoutBinding, item: Movie) {
        binding.movie = item
    }
}