package kr.ac.hansung.maldives.web.scheduler;

import org.rosuda.REngine.Rserve.RConnection;
import org.rosuda.REngine.Rserve.RserveException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Profile;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@Profile("product")
public class RecommendScheduler {

	private static final Logger log = LoggerFactory.getLogger(RecommendScheduler.class);
	
	@Scheduled(cron="0 */15 * * * *")
	public void buildModel(){
		RConnection connection = null;
		
		try {
			long startTime = System.currentTimeMillis();
			
			connection = new RConnection("localhost", 6311);
			connection.eval("try(createModel(), silent = TRUE)");
			connection.close();
			
			long endTime = System.currentTimeMillis();
			
			log.info("[R 모델 생성] 완료 (작동시간: {}ms)", endTime - startTime);
		} catch (RserveException e) {
			e.printStackTrace();
			log.warn("[R 모델 생성] 실패 - 예외 발생 (execption: {})", e.toString());
		}
	}
}
