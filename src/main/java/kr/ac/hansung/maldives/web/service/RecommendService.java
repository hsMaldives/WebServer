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

	public List<Store> getRecommendUBStore(long user_idx) {
		RConnection connection = null;

		List<Store> mine = new ArrayList<Store>();
		List<Evaluation> e = evaluationService.findByUser_idxAndNotEmptyEvaluation(user_idx);
		for (int i = 0; i < e.size(); i++) {
				Store store = e.get(i).getPosition().getStore();
				if (!mine.contains(store)) {
					mine.add(store);
				}
		}
		e.clear();
		System.out.println("111완");
		List<Store> ls = new ArrayList<Store>();
		try {
			connection = new RConnection("localhost", 6311);
			// connection.login("whereyou", "whereyou@");
			//connection.eval("try(source(\"//Users//Heemin//userbased.R\"))");
			//connection.eval("try(createUBModel(" + user_idx + "), silent = TRUE)");
			System.out.println("222완");
			String a = connection.eval("sim$user_id[2]").asString();
			System.out.println("333완");
			if (a.equals("NaN")) {
				e = evaluationService.findByUser_idxAndNotEmptyEvaluation(Long.parseLong(a));
				for (int i = 0; i < e.size(); i++) {
					Store store = e.get(i).getPosition().getStore();
					if (!ls.contains(store) && !mine.contains(store)) {
						ls.add(store);
					}
				}
			}
			connection.close();
			return ls;
		} catch (RserveException | REXPMismatchException ee) {
			ee.printStackTrace();
		} finally {
			connection.close();
		}
		connection.close();
		return null;
	}

	public List<Store> getRecommendIBStore(long user_idx) {
		RConnection connection = null;

		
		List<Store> mine = new ArrayList<Store>();
		List<Evaluation> e = evaluationService.findByUser_idxAndNotEmptyEvaluation(user_idx);
		for (int i = 0; i < e.size(); i++) {
				Store store = e.get(i).getPosition().getStore();
				if (!mine.contains(store)) {
					mine.add(store);
				}
		}
		e.clear();
		System.out.println("111완");

		
		List<Store> ls = new ArrayList<Store>();
		try {
			connection = new RConnection("localhost", 6311);
			// connection.login("whereyou", "whereyou@");
			
			//connection.eval("try(source(\"//Users//Heemin//itembased.R\"))");
			//connection.eval("try(createIBModel(), silent = TRUE)");
			System.out.println("222완");

			e = evaluationService.findByUser_idxAndNotEmptyEvaluation(user_idx);
			for (int i = 0; i < e.size(); i++) {
				String a = null;
				a = connection.eval("data.neighbours[rownames(data.neighbours)=="
						+ e.get(i).getPosition().getStore().getStoreIdx() + ",]").asString();
				System.out.println("33완");

				if (a != null) {
					Store store = storeService.getStoreById(Long.parseLong(a));
					if (!ls.contains(store) && !mine.contains(store)) {
						ls.add(store);
					}

				} else
					System.out.println("a == null error");

			}
			connection.close();
			return ls;
		} catch (RserveException | REXPMismatchException ee) {
			ee.printStackTrace();
		} finally {
			connection.close();
		}
		connection.close();
		return null;
	}

}
