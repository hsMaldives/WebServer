package kr.ac.hansung.maldives.web.service;

import java.io.IOException;
import java.io.InputStream;
import java.security.GeneralSecurityException;
import java.time.ZoneId;
import java.util.Collections;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.util.DateTime;
import com.google.api.services.fusiontables.Fusiontables;
import com.google.api.services.fusiontables.FusiontablesScopes;

import kr.ac.hansung.maldives.web.model.Evaluation;
import kr.ac.hansung.maldives.web.model.EvaluationFusionTable;
import kr.ac.hansung.maldives.web.model.Position;
import kr.ac.hansung.maldives.web.model.Store;
import kr.ac.hansung.maldives.web.model.User;
import kr.ac.hansung.maldives.web.property.WhereyouProperty;

import com.google.api.services.fusiontables.Fusiontables.Query.Sql;

@Service
public class FusionTableService implements InitializingBean, DisposableBean{
	
	private static final Logger log = LoggerFactory.getLogger(FusionTableService.class);

	private HttpTransport httpTransport;
	private Fusiontables fusiontables;
	private String fusionTableId;
	
	@Override
	public void afterPropertiesSet() throws Exception {
		try {
			JsonFactory jsonFactory = JacksonFactory.getDefaultInstance();
			Credential credential = authorize();
			httpTransport = GoogleNetHttpTransport.newTrustedTransport();
			
			fusiontables = new Fusiontables.Builder(httpTransport, jsonFactory, credential).build();
			
		} catch (GeneralSecurityException | IOException e) {
			log.error("[FusionTable Service] 오류 - 초기화 중 예외 발생 (e: {})", e.toString());
		}
	}
	
	@Override
	public void destroy() throws Exception {
		httpTransport.shutdown();
	}

	public void insertData(EvaluationFusionTable evaluationFusionTable) throws IOException {
		String sqlString = 
				"INSERT "
				+ "INTO " + fusionTableId + " (Eva1, Eva2, Eva3, Eva4, Eva5, Location, Category, Age, JobIdx, Sex, Date) " 
				+ "VALUES (" + evaluationFusionTable.getEva1() + ", " + evaluationFusionTable.getEva2() + ", " + evaluationFusionTable.getEva3() + ", "
							+ evaluationFusionTable.getEva4() + ", " + evaluationFusionTable.getEva5() + ", "
							+ "'" + evaluationFusionTable.getLocation() + "', '" + evaluationFusionTable.getCategory() + "', " + evaluationFusionTable.getAge() + ", "
							+ evaluationFusionTable.getJobIdx() + ", " + evaluationFusionTable.getSex() + ", " 
							+ "'" + new DateTime(evaluationFusionTable.getDate()) + "')";
		
		Sql sql = fusiontables.query().sql(sqlString);
		sql.execute();
	}
	
	public void addEvaluation(Evaluation evaluation) {
		EvaluationFusionTable evaluationFusionTable = evaluationFusionTableMapping(evaluation);
		
		try{
			insertData(evaluationFusionTable);
		} catch (IOException e) {
			log.warn("[FusionTable 추가] 실패 - 예외 발생 ({})", e.toString());
		}
		
	}
	
	public EvaluationFusionTable evaluationFusionTableMapping (Evaluation evaluation) {
		EvaluationFusionTable evaluationFusionTable = new EvaluationFusionTable();
		
		Position position = evaluation.getPosition();
		Store store = position.getStore();
		User user = position.getUser();
		
		evaluationFusionTable.setEva1(evaluation.getEva1());
		evaluationFusionTable.setEva2(evaluation.getEva2());
		evaluationFusionTable.setEva3(evaluation.getEva3());
		evaluationFusionTable.setEva4(evaluation.getEva4());
		evaluationFusionTable.setEva5(evaluation.getEva5());
		
		evaluationFusionTable.setLatitude(store.getLatitude());
		evaluationFusionTable.setLongitude(store.getLongitude());
		evaluationFusionTable.setCategory(store.getCategory().getCategoryCode());
		
		evaluationFusionTable.setAge(user.getAge());
		evaluationFusionTable.setJobIdx(user.getJob().getJobIdx());
		evaluationFusionTable.setSex(user.getSex().ordinal());
		
		evaluationFusionTable.setDate(Date.from(position.getTime().atZone(ZoneId.systemDefault()).toInstant()));
		
		return evaluationFusionTable;
	}

	@Autowired
	public void setFusionTableId(WhereyouProperty whereyouProperty) {
		this.fusionTableId = whereyouProperty.getFusionTableId();
	}

	public String getFusionTableId() {
		return fusionTableId;
	}
	
	private Credential authorize() throws IOException {
		InputStream authFileStream = getClass().getClassLoader().getResourceAsStream("auth/WhereYou-3c4058b728ca.json");
		
		GoogleCredential credential = GoogleCredential
				.fromStream(authFileStream)
				.createScoped(Collections.singleton(FusiontablesScopes.FUSIONTABLES));
		
		return credential;
	}
	
}
