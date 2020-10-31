package com.joytekmotion.yemilicious.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.joytekmotion.yemilicious.R
import com.joytekmotion.yemilicious.data.ProfileViewModel
import com.joytekmotion.yemilicious.models.Shop
import com.joytekmotion.yemilicious.models.User
import kotlinx.android.synthetic.main.fragment_profile_setup.*

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
private const val TAG = "ProfileSetupFragment"
class ProfileSetupFragment : Fragment() {

    private val args: ProfileSetupFragmentArgs by navArgs()
    private val updateProfileVm: ProfileViewModel by viewModels()

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile_setup, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        updateProfileVm.updateSuccess.observe(viewLifecycleOwner, { Log.d(TAG, "onViewCreated: profile updated success") })
        updateProfileVm.updateFailed.observe(viewLifecycleOwner, { Log.d(TAG, "onViewCreated: profile cannot be updated") })

        if (args.userRole == BUYER_ROLE) {
            edtShopName.isVisible = false
            edtAddress.hint = getString(R.string.home_address)
        }

        // Complete App Setup
        btnFinishSetup.setOnClickListener {
            val user = User()

            val address = edtAddress.text.toString().trim()
            val phoneNumber = edtPhoneNumber.text.toString().trim()

            if (args.userRole == BUYER_ROLE) {
                user.address = address
                user.role = BUYER_ROLE
            } else {
                user.shop = Shop(edtShopName.text.toString().trim(), address)
                user.role = SELLER_ROLE
            }

            user.phone = phoneNumber
            updateProfileVm.updateProfile(user)
        }


        // Navigate to the roles fragment
        btnBack.setOnClickListener {
            findNavController().navigate(R.id.RoleSetupFragment)
        }
    }
}