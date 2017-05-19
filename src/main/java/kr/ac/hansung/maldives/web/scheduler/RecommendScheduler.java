package kr.ac.hansung.maldives.web.scheduler;

import java.util.ArrayList;
import java.util.List;

import org.rosuda.REngine.REXPMismatchException;
import org.rosuda.REngine.Rserve.RConnection;
import org.rosuda.REngine.Rserve.RserveException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Profile;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import kr.ac.hansung.maldives.web.model.Evaluation;
import kr.ac.hansung.maldives.web.model.Store;

@Component
@Profile("product")
public class RecommendScheduler {

	private static final Logger log = LoggerFactory.getLogger(RecommendScheduler.class);
	
//	@Scheduled(cron="0 */15 * * * *")
//	public void buildModel(){
//		RConnection connection = null;
//		
//		try {
//			long startTime = System.currentTimeMillis();
//			
//			connection = new RConnection("localhost", 6311);
//			connection.eval("try(createModel(), silent = TRUE)");
//			connection.close();
//			
//			long endTime = System.currentTimeMillis();
//			
//			log.info("[R 모델 생성] 완료 (작동시간: {}ms)", endTime - startTime);
//		} catch (RserveException e) {
//			e.printStackTrace();
//			log.warn("[R 모델 생성] 실패 - 예외 발생 (execption: {})", e.toString());
//		}
//	}
	@Scheduled(cron="0 */15 * * * *")
	public void buildModel(){
		RConnection connection = null;
		
		try {
			long startTime = System.currentTimeMillis();
			
			connection = new RConnection("localhost", 6311);

			connection.eval("try(source(\"/home/whereyou/itembased.R\"))");
			connection.eval("try(createIBModel(), silent = TRUE)");
			
			//connection.eval("try(source(\"//Users//Heemin//userbased.R\"))");

			
			connection.close();
			
			long endTime = System.currentTimeMillis();
			
			log.info("[R 모델 생성] 완료 (작동시간: {}ms)", endTime - startTime);
		} catch (RserveException e) {
			e.printStackTrace();
			log.warn("[R 모델 생성] 실패 - 예외 발생 (execption: {})", e.toString());
		}
	}
//
//			//connection.eval("try(createUBModel(" + user_idx + "), silent = TRUE)");
//			String a = connection.eval("sim$user_id[2]").asString();
//			System.out.println("333완");
//			if (a.equals("NaN")) {
//				e = evaluationService.findByUser_idxAndNotEmptyEvaluation(Long.parseLong(a));
//				for (int i = 0; i < e.size(); i++) {
//					Store store = e.get(i).getPosition().getStore();
//					if (!ls.contains(store) && !mine.contains(store)) {
//						ls.add(store);
//	
//						
//	
	
}
