package com.moringaschool.recipestore.ui;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.moringaschool.recipestore.R;

import com.moringaschool.recipestore.models.Meal;
import com.squareup.picasso.Picasso;

import org.parceler.Parcels;

import butterknife.BindView;
import butterknife.ButterKnife;

public class EachMealFragment extends Fragment {
    @BindView(R.id.mealImageView) ImageView mImage;
    @BindView(R.id.mealNameTextView) TextView mName;
    @BindView(R.id.categoryTextView) TextView mCategory;
    @BindView(R.id.linkTextView) TextView mLink;
    @BindView(R.id.addMealButton) TextView mAddMealButton;

    private Meal mMeals;

    public EachMealFragment() {
        // Required empty public constructor
    }

    public static EachMealFragment newInstance(Meal mMeals) {
        EachMealFragment EachMealFragment = new EachMealFragment();
        Bundle args = new Bundle();
        args.putParcelable("meals", Parcels.wrap(mMeals));
        EachMealFragment.setArguments(args);
        return EachMealFragment;
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mMeals = Parcels.unwrap(getArguments().getParcelable("meals"));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_each_meal, container, false);
        ButterKnife.bind(this, view);

        Picasso.get().load(mMeals.getStrMealThumb()).into(mImage);

//        List<String> categories = new ArrayList<>();
//
//        for (CategoriesStore category: mMeals.getStrCategory()) {
//            categories.add(category.getCategories());
//        }

        mName.setText(mMeals.getStrMeal());
        mCategory.setText(mMeals.getStrCategory());
        mLink.setText(mMeals.getStrYoutube());

        return view;
    }
}