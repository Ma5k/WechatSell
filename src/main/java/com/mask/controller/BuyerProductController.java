package com.mask.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mask.VO.ProductInfoVO;
import com.mask.VO.ProductVO;
import com.mask.VO.ResultVO;
import com.mask.dataobject.ProductCategory;
import com.mask.dataobject.ProductInfo;
import com.mask.service.CategoryService;
import com.mask.service.ProductService;
import com.mask.utils.ResultVOUtil;

@RestController
@RequestMapping("/buyer/product")
public class BuyerProductController {
	
	@Autowired
	private ProductService productService;
	
	@Autowired 
	private CategoryService categoryService;
	
	@GetMapping("/list")
//	@Cacheable(cacheNames = "product", key = "#sellerId", condition = "#sellerId.length() > 3", unless = "#result.getCode() != 0")	//满足condition时才会执行缓存  不满足unless后的条件才会执行缓存
//	public ResultVO list(@RequestParam("sellerId") String sellerId) {
	public ResultVO list() {
		//1、查询所有的上架商品
		List<ProductInfo> productInfoList = productService.findUpAll();
		
		//2、查询类目（一次性查询）
		/*//传统方法
		List<Integer> categoryTypeList = new ArrayList<>();
		for (ProductInfo productInfo : productInfoList) {
			categoryTypeList.add(productInfo.getCategoryType());
		}
		*/
		
		//精简方法（Java8，lambda）
		List<Integer> categoryTypeList = productInfoList.stream()
				.map(e -> e.getCategoryType())
				.collect(Collectors.toList());
		List<ProductCategory> productCategoryList = categoryService.findByCategoryTypeIn(categoryTypeList);
		
		//3、数据拼装
		List<ProductVO> productVOList = new ArrayList<>();
		for (ProductCategory productCategory : productCategoryList) {
			ProductVO productVO = new ProductVO();
			productVO.setCategorytype(productCategory.getCategoryType());
			productVO.setCategoryName(productCategory.getCategoryName());
			
			List<ProductInfoVO> productInfoVOList = new ArrayList<>();
			for (ProductInfo productInfo : productInfoList) {
				if(productInfo.getCategoryType().equals(productCategory.getCategoryType())) {
					ProductInfoVO productInfoVO = new ProductInfoVO();
					BeanUtils.copyProperties(productInfo, productInfoVO);
					productInfoVOList.add(productInfoVO);
				}
			}
			productVO.setProductInfoVOList(productInfoVOList);
			productVOList.add(productVO);
		}
		
		return ResultVOUtil.success(productVOList);
	}
}
