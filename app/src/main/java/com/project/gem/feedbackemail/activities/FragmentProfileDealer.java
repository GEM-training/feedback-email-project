package com.project.gem.feedbackemail.activities;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.project.gem.feedbackemail.R;
import com.project.gem.feedbackemail.SQLDatabase.DealerAdapter;
import com.project.gem.feedbackemail.SQLDatabase.UserAdapter;
import com.project.gem.feedbackemail.model.Dealer;
import com.project.gem.feedbackemail.model.ResponseDTO;
import com.project.gem.feedbackemail.model.User;
import com.project.gem.feedbackemail.model.UserInfo;
import com.project.gem.feedbackemail.retrofit.RestClient;
import com.project.gem.feedbackemail.util.Constant;
import com.project.gem.feedbackemail.util.NetworkUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;

/**
 * Created by nghicv on 19/02/2016.
 */
public class FragmentProfileDealer extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.layout_profile, null);

        final TextView tvName = (TextView) view.findViewById(R.id.tvName);
        final EditText tvId = (EditText) view.findViewById(R.id.edId);
        TextView tvBirthday = (TextView) view.findViewById(R.id.tvBirthday);
        final EditText tvCountry = (EditText) view.findViewById(R.id.edCountry);

        SharedPreferences sharedPreferences = getActivity().getSharedPreferences(getString(R.string.share_preferences_file),
                Context.MODE_PRIVATE);
        int userId = sharedPreferences.getInt(Constant.USER_ID, -1);


        if (userId != -1) {
            if (NetworkUtil.isNetworkAvaiable(getContext())) {
                SharedPreferences sharedPreference = getActivity().getSharedPreferences(getString(R.string.share_preferences_file),
                        Context.MODE_PRIVATE);
                String token = sharedPreference.getString(Constant.TOKEN_KEY, "");
                RestClient.GitApiInterface service = RestClient.getClient();
                Call<ResponseDTO> call = service.getInfo(token, userId);
                call.enqueue(new Callback<ResponseDTO>() {
                    @Override
                    public void onResponse(Response<ResponseDTO> response) {
                        ResponseDTO responseDTO = response.body();
                        Dealer dealer = new Gson().fromJson(new Gson().toJson(responseDTO.getData()), Dealer.class);

                        tvName.setText(dealer.getName());
                        tvId.setText(1 + "");
                        tvCountry.setText(dealer.getAddress());
                    }

                    @Override
                    public void onFailure(Throwable t) {
                        Toast.makeText(getContext(), t.getMessage(), Toast.LENGTH_LONG).show();
                    }

                });
            } else {
                DealerAdapter dealerAdapter = new DealerAdapter(getActivity());
                Dealer dealer = dealerAdapter.getDealerById(userId);

                tvName.setText(dealer.getName());
                tvId.setText(dealer.getDealerId()+"");
                tvCountry.setText(dealer.getAddress());
            }
        }

        return view;

    }
}
