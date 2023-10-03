package com.example.cs388_project_3_flixster_p1

import com.example.cs388_project_3_flixster_p1.Movies

/**
 * This interface is used by the [MoviesRecyclerViewAdapter] to ensure
 * it has an appropriate Listener.
 *
 * In this app, it's implemented by [FlixsterFragment]
 */
interface OnListFragmentInteractionListener {
    abstract val progressBar: Any

    fun onItemClick(item: Movies)
}
