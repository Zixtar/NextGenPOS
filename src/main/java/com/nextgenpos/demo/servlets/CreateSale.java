package com.nextgenpos.demo.servlets;

import com.nextgenpos.demo.common.ProductDto;
import com.nextgenpos.demo.common.SaleItemCreatorDto;
import com.nextgenpos.demo.ejb.ProductsBean;
import com.nextgenpos.demo.ejb.SaleCreateBean;
import com.nextgenpos.demo.ejb.UsersBean;
import com.nextgenpos.demo.entities.Product;
import jakarta.inject.Inject;
import jakarta.json.JsonReader;
import jakarta.json.bind.serializer.JsonbSerializer;
import jakarta.json.stream.JsonParser;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "CreateSale", value = "/CreateSale")
public class CreateSale extends HttpServlet {

    @Inject
    ProductsBean productsBean;


    SaleCreateBean saleCreateBean;
    private static final String SALE_BEAN_SESION_KEY
            = "saleCreateBean";
    @Inject
    UsersBean usersBean;


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        saleCreateBean = (SaleCreateBean) request.getSession().getAttribute(SALE_BEAN_SESION_KEY);

        if (saleCreateBean == null) {
            try {
                InitialContext ic = new InitialContext();                                //this is bs
                saleCreateBean = (SaleCreateBean)                                        //how can everything else in JEE be so straight forward
                        ic.lookup("java:global/demo-1.0-SNAPSHOT/SaleCreateBean"); //but using a Stateful entity in a Servlet so convoluted
                request.getSession().setAttribute(SALE_BEAN_SESION_KEY, saleCreateBean); //I understand why this has to be done, I don't understand why this
            } catch (                                                                    //has to be written by hand and not just another annotation
                    NamingException e) {                                                 //also why did I need to search the lookup value in the server log
                throw new RuntimeException(e);                                           //how is that sane, why not a nice xml file or smth?
            }
        }
        if (saleCreateBean.iSFinalizeSale() || saleCreateBean.iSEmptySale()) {
            saleCreateBean.InitializeSale(request.getRemoteUser());
        }
        SetSaleID(request);
        VerifyAddProduct(request);

        Integer totalPrice = saleCreateBean.getTotal();
        List<SaleItemCreatorDto> saleProducts = saleCreateBean.getCurrentSaleProducts();
        request.setAttribute("saleProducts", saleProducts);
        request.setAttribute("totalPrice", totalPrice);
        request.getRequestDispatcher("/WEB-INF/pages/CreateSale.jsp").forward(request, response);
    }

    private void VerifyAddProduct(HttpServletRequest request) {
        String id = request.getParameter("id");
        if(id != null) {
            Long productId = Long.parseLong(id);
            ProductDto product = productsBean.findById(productId);

            SaleItemCreatorDto SaleDTO =  saleCreateBean.CopyProductDTOtoSaleDTO(product);
            List<Long> allPhotos = productsBean.findPhotosIdByProductId(product.getId());
            if(allPhotos != null && !allPhotos.isEmpty())
                SaleDTO.setPhotoId(allPhotos.get(0));

            saleCreateBean.AddItemToSale(SaleDTO);
        }
    }

    private void SetSaleID(HttpServletRequest request) {
        String saleID = saleCreateBean.getSaleID().toString();
        while(saleID.length()<20) {saleID = "0" + saleID;}
        request.setAttribute("saleID",saleID);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String body = getBody(request);
        String[] pieces = body.split("[\"]");
        Integer index =Integer.parseInt(pieces[3]);      //this is horrible
        String action = pieces [7]; //I know...I'm sorry

        switch (action){
            case "increaseQty":
                saleCreateBean.IncreaseQuantityForItem(index);
                break;
            case "decreaseQty":
                saleCreateBean.DecreaseQuantityForItem(index);
                break;
            case "removeItm":
                saleCreateBean.RemoveItem(index);
                break;
        }
        }

    public static String getBody(HttpServletRequest request) throws IOException {

        String body = null;
        StringBuilder stringBuilder = new StringBuilder();
        BufferedReader bufferedReader = null;

        try {
            InputStream inputStream = request.getInputStream();
            if (inputStream != null) {
                bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                char[] charBuffer = new char[128];
                int bytesRead = -1;
                while ((bytesRead = bufferedReader.read(charBuffer)) > 0) {
                    stringBuilder.append(charBuffer, 0, bytesRead);
                }
            } else {
                stringBuilder.append("");
            }
        } catch (IOException ex) {
            throw ex;
        } finally {
            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                } catch (IOException ex) {
                    throw ex;
                }
            }
        }

        body = stringBuilder.toString();
        return body;
    }
}
