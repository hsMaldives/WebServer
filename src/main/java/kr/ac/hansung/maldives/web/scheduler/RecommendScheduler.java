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
	

	@Scheduled(cron="0 * */3 * * *")
	public long buildUBCFModel(){
		RConnection connection = null;
		long uptime = 0;
		
		try {
			long startTime = System.currentTimeMillis();
			
			log.info("[R_ubcf 모델 생성] 시작");
			
			connection = new RConnection("localhost", 6311);

			connection.eval("try(source(\"/home/whereyou/cf.R\"))");
			connection.eval("try(saveUBCF(), silent = TRUE)");
			
			connection.close();
			
			long endTime = System.currentTimeMillis();
			
			uptime = endTime - startTime;
			
			log.info("[R_ubcf 모델 생성] 완료 (작동시간: {}ms)", uptime);
		} catch (RserveException e) {
			e.printStackTrace();
			log.warn("[R_ubcf 모델 생성] 실패 - 예외 발생 (execption: {})", e.toString());
		}
		
		return uptime;
	}

	@Scheduled(cron="0 */15 * * * *")
	public long buildIBCFModel(){
		RConnection connection = null;
		long uptime = 0;
		
		try {
			long startTime = System.currentTimeMillis();
			
			log.info("[R_ibcf 모델 생성] 시작");
			
			connection = new RConnection("localhost", 6311);

			connection.eval("try(source(\"/home/whereyou/cf.R\"))");
			connection.eval("try(saveIBCF(), silent = TRUE)");

			connection.close();
			
			long endTime = System.currentTimeMillis();
			
			uptime = endTime - startTime;
			
			log.info("[R_ibcf 모델 생성] 완료 (작동시간: {}ms)", uptime);
		} catch (RserveException e) {
			e.printStackTrace();
			log.warn("[R_ibcf 모델 생성] 실패 - 예외 발생 (execption: {})", e.toString());
		}
		
		return uptime;
	}
}
