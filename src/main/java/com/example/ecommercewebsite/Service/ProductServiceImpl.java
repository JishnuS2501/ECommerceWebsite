package com.example.ecommercewebsite.Service;

import com.example.ecommercewebsite.DTO.FakeStoreProductDTO;
import com.example.ecommercewebsite.Model.Category;
import com.example.ecommercewebsite.Model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.ResponseExtractor;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class ProductServiceImpl implements IProductService {
    @Autowired
    RestTemplateBuilder restTemplateBuilder;

    @Override
    public Product getProductById(long id) {
        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<FakeStoreProductDTO> fakeStoreResponse =  restTemplate.getForEntity("https://fakestoreapi.com/products/{id}", FakeStoreProductDTO.class, id);
        if(fakeStoreResponse.getStatusCode().is2xxSuccessful() && fakeStoreResponse.getBody()!=null){
            return from(fakeStoreResponse.getBody());
        }

        return null;
    }

    @Override
    public Product replaceProductById(long id, Product product) {
        FakeStoreProductDTO fakeStoreProductRequestDTO = from(product);
        ResponseEntity<FakeStoreProductDTO> fakeStoreProductDTOResponseEntity = postForEntity("https://fakestoreapi.com/products/{id}", fakeStoreProductRequestDTO, FakeStoreProductDTO.class, id);
        if(fakeStoreProductDTOResponseEntity.getStatusCode().is2xxSuccessful() && fakeStoreProductDTOResponseEntity.getBody()!= null){
            return from(fakeStoreProductDTOResponseEntity.getBody());
        }
        return null;
    }

    @Override
    public Product deleteProductById(long productId) {
        return null;
    }

    @Override
    public List<Product> getAllProduct() {
        return List.of();
    }

    @Override
    public Product updateProductById(long productId, Product product) {
        return null;
    }

    @Override
    public Product addProduct(Product product) {
        return null;
    }

    public <T> ResponseEntity<T> postForEntity(String url, @Nullable Object request,
                                               Class<T> responseType, Object... uriVariables) throws RestClientException {

        RestTemplate restTemplate = restTemplateBuilder.build();
        RequestCallback requestCallback = restTemplate.httpEntityCallback(request, responseType);
        ResponseExtractor<ResponseEntity<T>> responseExtractor = restTemplate.responseEntityExtractor(responseType);
        return restTemplate.execute(url, HttpMethod.PUT, requestCallback, responseExtractor, uriVariables);
    }

    private Product from(FakeStoreProductDTO fakeStoreProductDTO){
        Product product = new Product();
        product.setId(fakeStoreProductDTO.getId());
        product.setName(fakeStoreProductDTO.getTitle());
        product.setPrice(fakeStoreProductDTO.getPrice());
        product.setDescription(fakeStoreProductDTO.getDescription());
        Category category = new Category();
        category.setName(fakeStoreProductDTO.getCategory());
        product.setCategory(category);
        return product;
    }

    private FakeStoreProductDTO from(Product product){
        FakeStoreProductDTO fakeStoreProductDTO = new FakeStoreProductDTO();
        fakeStoreProductDTO.setId(product.getId());
        fakeStoreProductDTO.setTitle(product.getName());
        fakeStoreProductDTO.setPrice(product.getPrice());
        fakeStoreProductDTO.setCategory(product.getCategory().getName());
        fakeStoreProductDTO.setDescription(product.getDescription());
        return fakeStoreProductDTO;
    }
}
