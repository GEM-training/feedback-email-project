package com.gem.nhom1.feedbackemail.screen.customer.listproduct;

import com.gem.nhom1.feedbackemail.sqlite.UnitAdapter;
import com.gem.nhom1.feedbackemail.sqlite.UnitOfDealerAdapter;
import com.gem.nhom1.feedbackemail.commom.Constant;
import com.gem.nhom1.feedbackemail.network.ServiceBuilder;
import com.gem.nhom1.feedbackemail.network.callback.BaseCallback;
import com.gem.nhom1.feedbackemail.network.dto.ListProductDTO;
import com.gem.nhom1.feedbackemail.network.entities.Dealer;
import com.gem.nhom1.feedbackemail.network.entities.UnitOfDealer;
import com.gem.nhom1.feedbackemail.network.entities.UnitPrice;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by nghicv on 25/02/2016.
 */
public class ProductListPresenterImp implements ProductListPresenter {
    private ProductListView productListView;

    private int sizeDefault = 10;

    private int dealerId;

    public ProductListPresenterImp(ProductListView productListView) {
        this.productListView = productListView;
    }


    @Override
    public void doLoadListProduct(Dealer dealer) {
        productListView.showProgress();

        dealerId = dealer.getDealerId();

        if(!Constant.offLineMode) {
            ServiceBuilder.getService().getListProduct(Constant.CURRENT_ACCESS_TOKEN, dealer.getDealerId() , 0 , sizeDefault).enqueue(baseCallback);
        } else {
            UnitOfDealerAdapter unitOfDealerAdapter = new UnitOfDealerAdapter(productListView.getContextBase());
            productListView.onLoadProductListSuccess(unitOfDealerAdapter.getListUnitOfDealer(dealerId , 0 , sizeDefault));
            productListView.onRequestSuccess();

        }

    }

    private BaseCallback baseCallback = new BaseCallback<ListProductDTO>() {
        @Override
        public void onError(int errorCode, String errorMessage) {
            productListView.onRequestError(errorCode, errorMessage);
        }

        @Override
        public void onResponse(Object o) {
            productListView.onRequestSuccess();
            List<UnitPrice> productListTemp = new Gson().fromJson(new Gson().toJson(o) , ( new ArrayList<UnitPrice>()).getClass());

            List<UnitPrice> unitPrices = new ArrayList<UnitPrice>();

            for(int i = 0 ; i< productListTemp.size() ; i++){
                UnitPrice unitPrice = new Gson().fromJson(new Gson().toJson(productListTemp.get(i)), UnitPrice.class);

                unitPrices.add(unitPrice);

                saveIntoSqlite(unitPrice);

            }

            productListView.onLoadProductListSuccess(unitPrices);



        }
    };

    private void saveIntoSqlite(UnitPrice unitPrice){

        // Luu vao SQLtile

        UnitAdapter unitAdapter = new UnitAdapter(productListView.getContextBase());
        UnitOfDealerAdapter unitOfDealerAdapter = new UnitOfDealerAdapter(productListView.getContextBase());
        // luu lai san pham vao sqlite
        unitAdapter.insert(unitPrice.getUnit());

        // luu lai san pham va gia
        UnitOfDealer  unitOfDealer = new UnitOfDealer();

        unitOfDealer.setPrice(unitPrice.getPrice());
        unitOfDealer.setUnitId(unitPrice.getUnit().getUnitId());
        unitOfDealer.setDealerId(dealerId);

        unitOfDealerAdapter.insert(unitOfDealer);

    }
}
