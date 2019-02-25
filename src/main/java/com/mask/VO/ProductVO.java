package com.mask.VO;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 商品（包含类目）
 * @author Mask
 *
 */
public class ProductVO implements Serializable{
	
	private static final long serialVersionUID = 3068837394749875883L;
	
	@JsonProperty("name")
	private String categoryName;
	
	@JsonProperty("type")
	private Integer categorytype;
	
	@JsonProperty("foods")
	private List<ProductInfoVO> productInfoVOList;

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public Integer getCategorytype() {
		return categorytype;
	}

	public void setCategorytype(Integer categorytype) {
		this.categorytype = categorytype;
	}

	public List<ProductInfoVO> getProductInfoVOList() {
		return productInfoVOList;
	}

	public void setProductInfoVOList(List<ProductInfoVO> productInfoVOList) {
		this.productInfoVOList = productInfoVOList;
	}
}
