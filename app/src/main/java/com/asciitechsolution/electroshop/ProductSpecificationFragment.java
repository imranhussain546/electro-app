package com.asciitechsolution.electroshop;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.Spanned;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.asciitechsolution.electroshop.adapter.ProductSpecificationAdapter;
import com.asciitechsolution.electroshop.model.ProductSpecificationModel;

import java.util.List;

public class ProductSpecificationFragment extends Fragment {

    public ProductSpecificationFragment() {
        // Required empty public constructor
    }

    private TextView specificationBody;
    public Spanned body;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

      View view= inflater.inflate(R.layout.fragment_product_specification, container, false);
        specificationBody=view.findViewById(R.id.tv_product_specficaton);
        specificationBody.setText(body);


        /*productSpecificationModelList.add(new ProductSpecificationModel(0, "General"));
        productSpecificationModelList.add(new ProductSpecificationModel(1, "No Technical Specification", "Aashirvaad Sugar Control Aata"));
        productSpecificationModelList.add(new ProductSpecificationModel(1, "No Technical Specification", "Aashirvaad Sugar Control Aata"));
        productSpecificationModelList.add(new ProductSpecificationModel(0, "Display"));
        productSpecificationModelList.add(new ProductSpecificationModel(1, "No Technical Specification", "Aashirvaad Sugar Control Aata"));
        productSpecificationModelList.add(new ProductSpecificationModel(1, "No Technical Specification", "Aashirvaad Sugar Control Aata"));
        productSpecificationModelList.add(new ProductSpecificationModel(0, "General"));
        productSpecificationModelList.add(new ProductSpecificationModel(1, "No Technical Specification", "Aashirvaad Sugar Control Aata"));
        productSpecificationModelList.add(new ProductSpecificationModel(1, "No Technical Specification", "Aashirvaad Sugar Control Aata"));
        productSpecificationModelList.add(new ProductSpecificationModel(0, "Display"));
        productSpecificationModelList.add(new ProductSpecificationModel(1, "No Technical Specification", "Aashirvaad Sugar Control Aata"));
        productSpecificationModelList.add(new ProductSpecificationModel(1, "No Technical Specification", "Aashirvaad Sugar Control Aata"));
        productSpecificationModelList.add(new ProductSpecificationModel(0, "General"));
        productSpecificationModelList.add(new ProductSpecificationModel(1, "No Technical Specification", "Aashirvaad Sugar Control Aata"));
        productSpecificationModelList.add(new ProductSpecificationModel(1, "No Technical Specification", "Aashirvaad Sugar Control Aata"));
        productSpecificationModelList.add(new ProductSpecificationModel(0, "Display"));
        productSpecificationModelList.add(new ProductSpecificationModel(1, "No Technical Specification", "Aashirvaad Sugar Control Aata"));
        productSpecificationModelList.add(new ProductSpecificationModel(1, "No Technical Specification", "Aashirvaad Sugar Control Aata"));
        productSpecificationModelList.add(new ProductSpecificationModel(0, "General"));
        productSpecificationModelList.add(new ProductSpecificationModel(1, "No Technical Specification", "Aashirvaad Sugar Control Aata"));
        productSpecificationModelList.add(new ProductSpecificationModel(1, "No Technical Specification", "Aashirvaad Sugar Control Aata"));
        productSpecificationModelList.add(new ProductSpecificationModel(0, "Display"));
        productSpecificationModelList.add(new ProductSpecificationModel(1, "No Technical Specification", "Aashirvaad Sugar Control Aata"));
        productSpecificationModelList.add(new ProductSpecificationModel(1, "No Technical Specification", "Aashirvaad Sugar Control Aata"));*/

      return view;
    }
}