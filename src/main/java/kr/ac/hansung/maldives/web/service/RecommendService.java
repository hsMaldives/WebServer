package kr.ac.hansung.maldives.web.service;

import static org.mockito.Matchers.anyLong;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.rosuda.REngine.REXP;
import org.rosuda.REngine.REXPMismatchException;
import org.rosuda.REngine.Rserve.RConnection;
import org.rosuda.REngine.Rserve.RserveException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.ac.hansung.maldives.web.model.Evaluation;
import kr.ac.hansung.maldives.web.model.Store;

@Transactional
@Service
public class RecommendService {

	@Autowired
	private StoreService storeService;

	@Autowired
	private EvaluationService evaluationService;
	
	public List<Store> getRecommendStore(long user_idx) {
		RConnection connection = null;

		List<Store> ls = new ArrayList<Store>();
		try {
			connection = new RConnection("localhost", 6311);
			// connection.login("whereyou", "whereyou@");
			connection.eval("try(source(\"//Users//Heemin//itemBased.R\"))");
			connection.eval("try(createModel(), silent = TRUE)");
	
			List<Evaluation> e = evaluationService.findByUser_idxAndNotEmptyEvaluation(user_idx);
			for(int i = 0; i<e.size();i++){
					System.out.println(e.get(i).getPosition().getStore().getStoreIdx());
					String a = null;
					try {
						a = connection.eval("data.neighbours[rownames(data.neighbours)=="+e.get(i).getPosition().getStore().getStoreIdx()+",]").asString();
					} catch (REXPMismatchException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				if(a != null){
					Store store = storeService.getStoreById(Long.parseLong(a));
					if(!ls.contains(store)){
						ls.add(store);
					}
				
				}
				else
					System.out.println("a == null error");
				

			}
			return ls;
		} catch (RserveException e) {
			e.printStackTrace();
		} finally {
			connection.close();
		}
		return null;
	}

}
