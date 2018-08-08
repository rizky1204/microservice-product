package system.service;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import system.domain.Product;
import system.exception.BankException;
import system.repository.ProductRepository;
import system.vo.CreateProductVO;
import system.vo.ListProductVO;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Slf4j
@Service
public class ProductService {


    @Autowired
    ProductRepository productRepository;

    @Transactional
    public Boolean createProduct(CreateProductVO vo){
        log.info("createProduct");
        Product product = productRepository.findByProductCode(vo.getProductCode());
        if(product != null){
            if(product.getProductCode().equals(vo.getProductCode())){
                throw new BankException("product code already exist");
            }
        }
        Product newProduct = new Product();
        newProduct.setCreatedBy("SYSTEM");
        newProduct.setLastModifiedDate(new Date());
        newProduct.setLastModifiedBy("SYSTEM");
        newProduct.setProductCode(vo.getProductCode());
        newProduct.setMerk(vo.getMerk());
        newProduct.setStock(vo.getStock());
        newProduct.setProductName(vo.getProductName());
        productRepository.save(newProduct);
        return  Boolean.TRUE;

    }

    @Transactional
    public Boolean deletProduct(String secureId){
        log.info("deleteUser");
        Product product = productRepository.findBySecureId(secureId);
        if(product == null) throw new BankException("secureId tidak ditemukan");
        productRepository.delete(product);
        return Boolean.TRUE;
    }

    @Transactional
    public Boolean updateProduct(String secureId , CreateProductVO vo){
        log.info("updateProduct");
        Product product = productRepository.findBySecureId(secureId);
        if(product == null) throw new BankException("secureId tidak ditemukan");
        product.setProductName(vo.getProductName());
        product.setProductCode(vo.getProductCode());
        product.setStock(vo.getStock());
        product.setMerk(vo.getMerk());
        productRepository.save(product);
        return Boolean.TRUE;
    }

    public List<CreateProductVO> listProduct(){
        log.info("listUser");
        List<Product> products = productRepository.findAll();
        ListProductVO listProductVO = new ListProductVO();
        List<CreateProductVO> voProduct = new ArrayList<>();
        for(Product product : products){
            CreateProductVO vo =  new CreateProductVO();
            vo.setProductName(product.getProductName());
            vo.setProductCode(product.getProductCode());
            vo.setMerk(product.getMerk());
            vo.setStock(product.getStock());
            voProduct.add(vo);
        }
        listProductVO.setListProduct(voProduct);
        return voProduct;
    }

}
