package com.uvaysss.store.data.repository.datasource;

import android.content.res.Resources;

import com.uvaysss.store.R;
import com.uvaysss.store.data.local.Product;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.inject.Inject;

public class ProductDataSourceImpl implements ProductDataSource {

    private Resources resources;

    @Inject
    public ProductDataSourceImpl(Resources resources) {
        this.resources = resources;
    }

    public List<Product> getProducts() {
        Scanner scanner = new Scanner(resources.openRawResource(R.raw.data));
        List<Product> products = new ArrayList<>();

        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();

            String title = line.split(",")[0].replace("\"", "");
            String price = line.split(",")[1].replace("\"", "").replace(" ", "");
            String count = line.split(",")[2].replace("\"", "").replace(" ", "");

            Product product = new Product(
                    title,
                    Double.valueOf(price),
                    Integer.valueOf(count)
            );

            products.add(product);
        }
        scanner.close();

        return products;
    }
}
