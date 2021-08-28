package com.example.demo.ui.main.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.demo.MainActivity
import com.example.demo.base.BaseFragment
import com.example.demo.base.BaseViewModelFactory
import com.example.demo.base.listener.EndlessRecyclerViewScrollListener
import com.example.demo.common.model.Photo
import com.example.demo.databinding.FragmentMainBinding
import com.example.demo.ui.login.view.LoginFragment
import com.example.demo.ui.main.view.adapter.PhotosAdapter
import com.example.demo.ui.main.viewmodel.MainViewModel
import com.example.demo.utils.Utils
import com.google.firebase.auth.FirebaseAuth
import timber.log.Timber


class MainFragment : BaseFragment() {


    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!
    private lateinit var mainViewModel: MainViewModel

    //    private var photoList: MutableList<Photo>? = null
    private lateinit var photosAdapter: PhotosAdapter
    private lateinit var auth: FirebaseAuth

    private var page: Int = 1
    private lateinit var endlessScrollListener: EndlessRecyclerViewScrollListener
//
//    var getAllUserFragment: GetAllUserFragment? = null
//    var mandoBkShopFragment: MandoBkShopFragment? = null
//    var nearByShopFragment: NearByShopFragment? = null
//    var myOrderFragment: MyOrderFragment? = null
//    private var tempString: String? = null
//    private var flagMessage = false


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMainBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    /**
     * This method is used to initialize views for this screen
     */
    private fun initView() {
        mainViewModel =
            ViewModelProvider(
                this,
                BaseViewModelFactory { MainViewModel() }).get(MainViewModel::class.java)
        auth = FirebaseAuth.getInstance()

        setAdapter()
        setObservers()
        setOnClickListeners()
    }


    /**
     * This method is used to set Recyclerview Data
     */
    private fun setAdapter() {

        //initialize Shop Type  RecyclerView
//        photoList = mutableListOf()
        binding.rvImages.setHasFixedSize(true)
        val gridManager: GridLayoutManager = GridLayoutManager(
            activity.applicationContext,
            3
        )
        binding.rvImages.layoutManager = gridManager
        if (Utils.isNetworkAvailable(requireActivity())) {
            val endlessScrollListener = object :
                EndlessRecyclerViewScrollListener(gridManager, 40) {
                override fun onLoadMore(page: Int, totalItemsCount: Int, view: RecyclerView?) {
                    this@MainFragment.page = page
                    Timber.d("page:- $page Load MOre")
                    callApiForGetStatus()

                }
            }
            callApiForGetStatus()

            binding.rvImages.addOnScrollListener(endlessScrollListener)
        } else {
            mainViewModel.getLocalPhotos()
        }

        photosAdapter = PhotosAdapter(activity)
        binding.rvImages.adapter = photosAdapter
    }

    /**
     * This method is used to set observers
     */
    private fun setObservers() {
        mainViewModel.photos.observe(viewLifecycleOwner, Observer { imageResponse ->
//            if (imageResponse.size < 30) {
//            }

//            photoList?.clear()
//            photoList?.addAll(imageResponse)
            photosAdapter.addItemList(page, imageResponse as MutableList<Photo>?)
        })
    }

    /**
     * This method is used to set click listeners for this screen
     */
    private fun setOnClickListeners() {
        binding.logOutTV.setOnClickListener(View.OnClickListener {
            auth.signOut()
            (activity as MainActivity).navigateToMainFragment(LoginFragment(), this)
        })
    }

    /**
     * This method is used to call Api for Get Status
     */
    private fun callApiForGetStatus() {
        mainViewModel.callGetPhotosApi(page, 40)
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}