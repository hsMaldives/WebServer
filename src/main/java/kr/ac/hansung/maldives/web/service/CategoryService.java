package kr.ac.hansung.maldives.web.service;

import java.text.DecimalFormat;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.ac.hansung.maldives.web.dao.CategoryRepository;
import kr.ac.hansung.maldives.web.model.Category;

@Service
public class CategoryService {
	
	private static final String CATEGORY_SPLIT_KEYWORD = " > ";
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	public Category getCategory(String categoryString){
		String splitCategorys[] = categoryString.split(CATEGORY_SPLIT_KEYWORD);
		Category currentCategory = null;
		String currentCategoryName = "";
		
		currentCategory = categoryRepository.findByFullName(categoryString);
		
		if(currentCategory != null){
			return currentCategory;
		}
		
		for(int depth=0; depth<splitCategorys.length; depth++){
			String trimCategory = splitCategorys[depth].trim();
			Category findCategory = categoryRepository.findByDepthAndName(depth, trimCategory);
			
			currentCategoryName += ((depth == 0) ? "" : CATEGORY_SPLIT_KEYWORD) + trimCategory;
			
			if(findCategory == null){
				String parentCategoryCode = "";
				String categoryCode = "";
				
				if(depth != 0){
					categoryCode = parentCategoryCode = currentCategory.getCategoryCode();
				}
				
				Category tempCategory = categoryRepository.findTop1ByDepthAndCategoryCodeStartingWithOrderByCategoryCodeDesc(depth, parentCategoryCode);
				
				if(tempCategory == null){
					categoryCode += "00";
				} else {
					categoryCode += new DecimalFormat("00").format(Integer.parseInt(tempCategory.getCategoryCode().substring(depth * 2, (depth * 2) + 2), 10) + 1);
				}
				
				currentCategory = Category.builder()
									.categoryCode(categoryCode)
									.depth(depth)
									.name(trimCategory)
									.fullName(currentCategoryName)
									.build();
				
				categoryRepository.save(currentCategory);
			} else {
				currentCategory = findCategory;
			}
		}
		
		return currentCategory;
	}
	
	public List<Category> findByDepthAndCategoryCodeStartingWithOrderByCategoryCode(int depth, String categoryCode){
		return categoryRepository.findByDepthAndCategoryCodeStartingWithOrderByCategoryCode(depth, categoryCode);
	}
}
