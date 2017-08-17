package com.shibuyaxpress.tecsup_eb.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.shibuyaxpress.tecsup_eb.R;


/**
 * Created by paulf on 4/28/2017.
 */

public class BeneficioTabFragment extends Fragment {
    public static TabLayout tabLayout;
    public static ViewPager viewPager;
    public static int int_items=4;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        /**
         *Inflate tab_layout and setup Views.
         */
        View x =  inflater.inflate(R.layout.content_beneficios,null);
        tabLayout = (TabLayout) x.findViewById(R.id.tabs_beneficio);
        viewPager = (ViewPager) x.findViewById(R.id.viewpager_beneficio);

        /**
         *Set an Apater for the View Pager
         */
        viewPager.setAdapter(new BeneficioTabFragment.MyAdapter(getChildFragmentManager()));

        /**
         * Now , this is a workaround ,
         * The setupWithViewPager dose't works without the runnable .
         * Maybe a Support Library Bug .
         */

        tabLayout.post(new Runnable() {
            @Override
            public void run() {
                tabLayout.setupWithViewPager(viewPager);
            }
        });

        return x;

    }

    class MyAdapter extends FragmentPagerAdapter {

        public MyAdapter(FragmentManager fm) {
            super(fm);
        }

        /**
         * Return fragment with respect to Position .
         */

        @Override
        public Fragment getItem(int position)
        {
            switch (position){
                case 0 : return new FirstFragmentBeneficio();
                case 1 : return new SecondFragmentBeneficio();
                case 2 : return new TriadFragmentBeneficio();
                case 3 : return new FourthFragmentBeneficio();
            }
            return null;
        }

        @Override
        public int getCount() {

            return int_items;

        }

        /**
         * This method returns the title of the tab according to the position.
         */

        @Override
        public CharSequence getPageTitle(int position) {

            switch (position){
                case 0 :
                    return "Educación";
                case 1 :
                    return "Salud";
                case 2:
                    return "Estilo de vida";
                case 3:
                    return "Alimentacion";
            }
            return null;
        }
    }

}
