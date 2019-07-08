package com.anamk.uasanamapi.ui.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.anamk.uasanamapi.BuildConfig
import com.anamk.uasanamapi.R
import com.anamk.uasanamapi.model.DataItemModel
import com.anamk.uasanamapi.rest.ApiService
import com.anamk.uasanamapi.rest.RetroConfig
import com.bumptech.glide.Glide
import de.hdodenhof.circleimageview.CircleImageView
import kotlinx.android.synthetic.main.fragment_profil.view.*
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

/**
 * A simple [Fragment] subclass.
 *
 */
class ProfilFragment : Fragment() {
    private var items: ArrayList<DataItemModel> = arrayListOf()
    private lateinit var npmTexView: TextView
    private lateinit var nik: TextView
    private lateinit var nisn: TextView
    private lateinit var tahun_masuk: TextView
    private lateinit var tgl_masuk: TextView
    private lateinit var kelamin: TextView
    private lateinit var transpor: TextView
    private lateinit var dosen_wali: TextView
    private lateinit var kelas: TextView
    private lateinit var ktlhr: TextView
    private lateinit var tlhr: TextView
    private lateinit var agama: TextView
    private lateinit var almt: TextView
    private lateinit var dusun: TextView
    private lateinit var kec: TextView
    private lateinit var prop: TextView
    private lateinit var telp: TextView
    private lateinit var kpos: TextView
    private lateinit var email: TextView
    private lateinit var darah: TextView
    private lateinit var alamat_kos: TextView
    private lateinit var ayah: TextView
    private lateinit var ibu: TextView


    private lateinit var ivImageProfile: CircleImageView
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view: View = inflater.inflate(R.layout.fragment_profil, container, false)
        ivImageProfile = view.ivImageProfile
        npmTexView = view.tvNpm
        nik = view.tvNik
        nisn = view.tvNISN
        tahun_masuk = view.tvtahun_masuk
        tgl_masuk = view.tvtgl_msk
        kelamin = view.tvkelamin
        transpor = view.tvtranspor
        dosen_wali = view.tvdosen_wali
        kelas = view.tvkelas
        ktlhr = view.tvktlhr
        tlhr = view.tvtlhr
        agama = view.tvagama
        almt = view.tvalmt
        dusun = view.tvdusun
        kec = view.tvkec
        prop = view.tvprop
        telp = view.tvtelp
        kpos = view.tvkpos
        email = view.tvemail
        darah = view.tvdarah
        alamat_kos = view.tvalamat_kos
        ayah = view.tvayah
        ibu = view.tvibu
        npmTexView.text = "16670009"

        getDatas()

        return view;
    }

    private fun getDatas() {
        val apiService: ApiService = RetroConfig.provideApi()
        apiService.getProfil()
            .subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                items.clear()
                items = it.data as ArrayList<DataItemModel>
                for (i: Int in items.indices) {
                    nik.text = items.get(i).nik
                    nisn.text = items.get(i).nisn
                    tahun_masuk.text = items.get(i).tahunMasuk
                    tgl_masuk.text = items.get(i).tglMsk
                    kelamin.text = items.get(i).kelamin
                    transpor.text = items.get(i).transpor
                    dosen_wali.text = items.get(i).dosenWali
                    kelas.text = items.get(i).kelas
                    ktlhr.text = items.get(i).ktlhr
                    tlhr.text = items.get(i).tlhr
                    agama.text = items.get(i).agama
                    almt.text = items.get(i).almt
                    dusun.text = items.get(i).dusun
                    kec.text = items.get(i).kec
                    prop.text = items.get(i).prop
                    telp.text = items.get(i).telp
                    kpos.text = items.get(i).kpos
                    email.text = items.get(i).email
                    darah.text = items.get(i).darah
                    alamat_kos.text = items.get(i).alamatKos
                    ayah.text = items.get(i).ayah
                    ibu.text = items.get(i).ibu
                    activity?.let { it1 ->
                        Glide.with(it1).load("http://informatika.upgris.ac.id/mobile/image/" + items.get(i).foto)
                            .override(512, 512).error(R.drawable.error_image).into(ivImageProfile)
                    }
                    if (BuildConfig.NPM.equals("16670009")) {
                        nik.text = "Kepo"
                        nisn.text = "Kepo"
                        activity?.let { it1 ->
                            Glide.with(it1).load("https://scontent-sin2-1.cdninstagram.com/vp/5ebb72ab9b823f645738d7d645fd3659/5DBE0301/t51.2885-19/s150x150/51877606_2287715151272514_2335104179019710464_n.jpg?_nc_ht=scontent-sin2-1.cdninstagram.com")
                                .override(512, 512).error(R.drawable.error_image).into(ivImageProfile)
                        }
                    }
                }


            }, {
                error("Error" + it.message)
            })

    }


}
