package utils;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.gss.common.MongoConstant;
import com.mongodb.client.MongoDatabase;

@Component
//@Service
//@SuppressWarnings({ "unchecked" })
public class MongoUtils {

	@Autowired
	private MongoTemplate mongoTemplate;
	
//	@Autowired
//	private MongoDatabase mongoDatabase;
	
//	private static final long MAX_DEPTH_SUPPORTED = 10000L;

	public <T> T findDocumentById(String documentId, Class<?> entityClass) {
		return (T) mongoTemplate.findById(documentId, entityClass);
	}
	
//	public <T> T findOneDocumentByKey(Map<String, ? extends Object> require, Class<?> entityClass) {
//		List<Criteria> criteria = new ArrayList<>();
//		Object[] keys = require.keySet().toArray();
//		for (int x = 0; x < require.size(); x++) {
//			criteria.add(Criteria.where((String) keys[x]).is(require.get(keys[x])));
//		}
//		Query query = new Query();
//		query.addCriteria(new Criteria().andOperator(criteria));
//		return (T) mongoTemplate.findOne(query, entityClass);
//	}
//	
//	public <T> T findOneDocumentByKey(Map<String, ? extends Object> require, Class<?> entityClass, String collectionName) {
//		List<Criteria> criteria = new ArrayList<>();
//		Object[] keys = require.keySet().toArray();
//		for (int x = 0; x < require.size(); x++) {
//			criteria.add(Criteria.where((String) keys[x]).is(require.get(keys[x])));
//		}
//		Query query = new Query();
//		query.addCriteria(new Criteria().andOperator(criteria));
//		return (T) mongoTemplate.findOne(query, entityClass, collectionName);
//	}
	
	public <T> T findOne(String _id, String id, Class<T> clazz) {
	    Query query = new Query(Criteria.where(_id).is(new ObjectId(id)));
	    var result = mongoTemplate.findOne(query, clazz);
	    return result;
	}

	public <T> List<T> findAll(Class<?> entityClass) {
		return (List<T>) mongoTemplate.findAll(entityClass);
	}
	
	public <T> List<T> findAllDTO(Class<?> entityClass, String collectionName) {
		return (List<T>) mongoTemplate.findAll(entityClass, collectionName);
	}
	
	public <T> List<T> findItemByField(String fieldName, Object fieldValue, Class<T> clazz) {
        Query query = new Query();
        query.addCriteria(Criteria.where(fieldName).is(fieldValue));
        return (List<T>) mongoTemplate.find(query, clazz);
    }


//	public long count(List<QueryParam> require, String collectionName) {
//		Query query = new Query();
//		buildCriteria(query, require);
//		return mongoTemplate.count(query, collectionName);
//	}

//	public void updateBulk(String keyName, String valueName, Map<String, ? extends Object> documents,
//			Class<?> entityClass) {
//		int count = 0;
//		int batch = 100;
//		BulkOperations bulkOps = mongoTemplate.bulkOps(BulkMode.UNORDERED, entityClass);
//		for (Entry<String, ? extends Object> keyValue : documents.entrySet()) {
//			Criteria criteria = Criteria.where(keyName).is(keyValue.getKey());
//			Query query = new Query(criteria);
//			Update update = new Update();
//			documents.entrySet().forEach(entry -> update.set(valueName, keyValue.getValue()));
//			bulkOps.upsert(query, update);
//			count++;
//			if (count == batch) {
//				bulkOps.execute();
//				count = 0;
//			}
//		}
//		if (count > 0) {
//			bulkOps.execute();
//		}
//	}
//
//	public void updateBulk(String keyName, Map<String, Map<String, Object>> documents, Class<?> entityClass) {
//		int count = 0;
//		int batch = 100;
//		BulkOperations bulkOps = mongoTemplate.bulkOps(BulkMode.UNORDERED, entityClass);
//		for (Entry<String, Map<String, Object>> doc : documents.entrySet()) {
//			Criteria criteria = Criteria.where(keyName).is(doc.getKey());
//			Query query = new Query(criteria);
//			Update update = new Update();
//			doc.getValue().entrySet().forEach(entry -> update.set(entry.getKey(), entry.getValue()));
//			bulkOps.upsert(query, update);
//			count++;
//			if (count == batch) {
//				bulkOps.execute();
//				count = 0;
//			}
//		}
//		if (count > 0) {
//			bulkOps.execute();
//		}
//	}
//
//	public <T> List<BulkWriteResult> bulkUpsert(String keyTag, String collectionName, Collection<T> documents,
//			Class<T> entityClass) {
//		int count = 0;
//		int batch = 100;
//		List<BulkWriteResult> results = new ArrayList<>();
//		BulkOperations bulkOps = mongoTemplate.bulkOps(BulkOperations.BulkMode.UNORDERED, entityClass, collectionName);
//		for (T document : documents) {
//			Document doc = new Document();
//			mongoTemplate.getConverter().write(document, doc);
//			Query query = new Query(Criteria.where(keyTag).is(doc.get(keyTag)));
//			Document updateDoc = new Document();
//			updateDoc.append("$set", doc);
//			Update update = Update.fromDocument(updateDoc, keyTag);
//			bulkOps.upsert(query, update);
//			count++;
//			if (count == batch) {
//				results.add(bulkOps.execute());
//				count = 0;
//			}
//		}
//		if (count > 0) {
//			results.add(bulkOps.execute());
//		}
//		return results;
//	}
//
//	public <T> void bulkUpsert(List<String> keyTags, String collectionName, Collection<T> documents,
//			Class<T> entityClass) {
//		int count = 0;
//		int batch = 100;
//		BulkOperations bulkOps = mongoTemplate.bulkOps(BulkOperations.BulkMode.UNORDERED, entityClass, collectionName);
//		for (T document : documents) {
//			Document doc = new Document();
//			mongoTemplate.getConverter().write(document, doc);
//			List<Criteria> criteria = new ArrayList<>();
//			for (String key : keyTags) {
//				criteria.add(Criteria.where(key).is(doc.get(key)));
//			}
//			Query query = new Query();
//			query.addCriteria(new Criteria().andOperator(criteria));
//			Document updateDoc = new Document();
//			updateDoc.append("$set", doc);
//			Update update = Update.fromDocument(updateDoc, keyTags.toArray(String[]::new));
//			bulkOps.upsert(query, update);
//			count++;
//			if (count == batch) {
//				bulkOps.execute();
//				count = 0;
//			}
//		}
//		if (count > 0) {
//			bulkOps.execute();
//		}
//	}
//
//	public <T> Page<T> filterDocumentPaging(Map<String, ? extends Object> require, Pageable pageable,
//			Class<?> entityClass) {
//		List<Criteria> criteria = new ArrayList<>();
//		Object[] keys = require.keySet().toArray();
//		for (int x = 0; x < require.size(); x++) {
//			criteria.add(Criteria.where((String) keys[x]).is(require.get(keys[x])));
//		}
//		Query query = new Query();
//		query.with(pageable);
//		if (!criteria.isEmpty()) {
//			query.addCriteria(new Criteria().andOperator(criteria));
//		}
//		List<T> items = (List<T>) mongoTemplate.find(query, entityClass);
//		Long count = mongoTemplate.count(query.skip(0).limit(0), entityClass);
//		return new PageImpl<>(items, pageable, count);
//	}
//	
//	public <T> Page<T> filterDocumentAndSubDocumentPaging(Map<String, ? extends Object> andQuery, Map<String, ? extends Object> orQuery, Pageable pageable,
//			Class<?> entityClass) {
//		List<Criteria> criteria = new ArrayList<>();
//		List<Criteria> subCriteria = new ArrayList<>();
//		Object[] keys = andQuery.keySet().toArray();
//		Object[] subKeys = orQuery.keySet().toArray();
//		for (int x = 0; x < andQuery.size(); x++) {
//			criteria.add(Criteria.where((String) keys[x]).is(andQuery.get(keys[x])));
//		}
//		for (int x = 0; x < orQuery.size(); x++) {
//			subCriteria.add(Criteria.where((String) subKeys[x]).is(orQuery.get(subKeys[x])));
//		}
//		Query query = new Query();
//		query.with(pageable);
//		if(!subCriteria.isEmpty()) {
//			criteria.add(new Criteria().orOperator(subCriteria));
//		}
//		if (!criteria.isEmpty()) {
//			query.addCriteria(new Criteria().andOperator(criteria));
//		}
//		List<T> items = (List<T>) mongoTemplate.find(query, entityClass);
//		Long count = mongoTemplate.count(query.skip(0).limit(0), entityClass);
//		return new PageImpl<>(items, pageable, count);
//	}
//	
//	public <T> Page<T> filterDocumentAndSubDocumentPagingAndCondition(Map<String, ? extends Object> andQuery, Map<String, ? extends Object> orQuery, Pageable pageable,
//			Class<?> entityClass) {
//		List<Criteria> criteria = new ArrayList<>();
//		List<Criteria> subCriteria1 = new ArrayList<>();
//		List<Criteria> subCriteria2 = new ArrayList<>();
//		List<Criteria> subCriteria3 = new ArrayList<>();
//		Object[] keys = andQuery.keySet().toArray();
//		Object[] subKeys = orQuery.keySet().toArray();
//		for (int x = 0; x < andQuery.size(); x++) {
//			Object[] valuesAnd = andQuery.values().toArray();
//			if (valuesAnd[x].toString().contains(",")) {
//				String[] valueOr = valuesAnd[x].toString().split(",");
//				for (String k : valueOr) {
//					criteria.add(Criteria.where((String) subKeys[x]).is(k));
//				}
//			} else {
//				criteria.add(Criteria.where((String) keys[x]).is(andQuery.get(keys[x])));
//			}
//		}
//		for (int x = 0; x < orQuery.size(); x++) {
//			Object[] valuesOr = orQuery.values().toArray();
//			if (subKeys[x].toString().contains("projectManagerShortName") || subKeys[x].toString().contains("coManager.projectCoManagerShortName")) {
//				if (valuesOr[x].toString().contains(",")) {
//					String[] valueOr = valuesOr[x].toString().split(",");
//					for (String k : valueOr) {
//						subCriteria1.add(Criteria.where((String) subKeys[x]).is(k));
//					}
//				} else {
//					subCriteria1.add(Criteria.where((String) keys[x]).is(andQuery.get(keys[x])));
//				}
//			}
//			if (subKeys[x].toString().contains("projectType")) {
//				if (valuesOr[x].toString().contains(",")) {
//					String[] valueOr = valuesOr[x].toString().split(",");
//					for (String k : valueOr) {
//						subCriteria2.add(Criteria.where((String) subKeys[x]).is(k));
//					}
//				} else {
//					subCriteria2.add(Criteria.where((String) keys[x]).is(andQuery.get(keys[x])));
//				}
//			}
//
//		}
//
//		Query query = new Query();
//		query.with(pageable);
//		if(!subCriteria1.isEmpty()) {
//			criteria.add(new Criteria().orOperator(subCriteria1));
//		}
//		if(!subCriteria2.isEmpty()) {
//			criteria.add(new Criteria().orOperator(subCriteria2));
//		}
//		if(!subCriteria3.isEmpty()) {
//			criteria.add(new Criteria().orOperator(subCriteria3));
//		}
//		if (!criteria.isEmpty()) {
//			query.addCriteria(new Criteria().andOperator(criteria));
//		}
//		List<T> items = (List<T>) mongoTemplate.find(query, entityClass);
//		Long count = mongoTemplate.count(query.skip(0).limit(0), entityClass);
//		return new PageImpl<>(items, pageable, count);
//	}
//	
//	public <T> Page<T> filterDocumentAndSubDocumentPagingProjects(Map<String, ? extends Object> andQuery, Map<String, ? extends Object> orQuery, Pageable pageable,
//			Class<?> entityClass) {
//		List<Criteria> criteria1 = new ArrayList<>();
//		List<Criteria> criteria2 = new ArrayList<>();
//		List<Criteria> criteria = new ArrayList<>();
//		List<Criteria> subCriteria1 = new ArrayList<>();
//		List<Criteria> subCriteria2 = new ArrayList<>();
//		List<Criteria> subCriteria3 = new ArrayList<>();
//		Object[] keys = andQuery.keySet().toArray();
//		Object[] subKeys = orQuery.keySet().toArray();
//		for (int x = 0; x < andQuery.size(); x++) {
//			Object[] valuesAnd = andQuery.values().toArray();
//			if (valuesAnd[x].toString().contains(",")) {
//				String[] valueOr = valuesAnd[x].toString().split(",");
//				for (String k : valueOr) {
//					criteria1.add(Criteria.where((String) keys[x]).is(k));
//				}
//			} else {
//				criteria1.add(Criteria.where((String) keys[x]).is(andQuery.get(keys[x])));
//			}
//		}
//		for (int x = 0; x < orQuery.size(); x++) {
//			Object[] valuesOr = orQuery.values().toArray();
//			if (subKeys[x].toString().contains("projectManagerShortName") || subKeys[x].toString().contains("projectCoManagerShortName")) {
//				if (valuesOr[x].toString().contains(",")) {
//					String[] valueOr = valuesOr[x].toString().split(",");
//					for (String k : valueOr) {
//						subCriteria1.add(Criteria.where((String) subKeys[x]).is(k));
//					}
//				} else {
//					subCriteria1.add(Criteria.where((String) subKeys[x]).is(orQuery.get(subKeys[x])));
//				}
//			}
//			if (subKeys[x].toString().contains("projectType")) {
//				if (valuesOr[x].toString().contains(",")) {
//					String[] valueOr = valuesOr[x].toString().split(",");
//					for (String k : valueOr) {
//						subCriteria2.add(Criteria.where((String) subKeys[x]).is(k));
//					}
//				} else {
//					subCriteria2.add(Criteria.where((String) subKeys[x]).is(orQuery.get(subKeys[x])));
//				}
//			}
//
//		}
//
//		Query query = new Query();
//		query.with(pageable);
//		if(!subCriteria1.isEmpty()) {
//			criteria2.add(new Criteria().orOperator(subCriteria1));
//		}
//		if(!subCriteria2.isEmpty()) {
//			criteria2.add(new Criteria().orOperator(subCriteria2));
//		}
//		if(!subCriteria3.isEmpty()) {
//			criteria2.add(new Criteria().orOperator(subCriteria3));
//		}
//		if(!criteria1.isEmpty()) {
//			criteria2.add(new Criteria().andOperator(criteria1));
//		}
//		if(!criteria2.isEmpty()) {
//			criteria.add(new Criteria().andOperator(criteria2));
//		}
//		if (!criteria.isEmpty()) {
//			query.addCriteria(new Criteria().orOperator(criteria));
//		}
//		List<T> items = (List<T>) mongoTemplate.find(query, entityClass);
//		Long count = mongoTemplate.count(query.skip(0).limit(0), entityClass);
//		return new PageImpl<>(items, pageable, count);
//	}
//	
//	
//	
//	public <T> Page<T> searchDocumentAndSubDocumentPagingProjects(Map<String, ? extends Object> andQuery, Map<String, ? extends Object> orQuery, Pageable pageable,
//			Class<?> entityClass) {
//		List<Criteria> criteria1 = new ArrayList<>();
//		List<Criteria> criteria2 = new ArrayList<>();
//		List<Criteria> criteria = new ArrayList<>();
//		List<Criteria> subCriteria1 = new ArrayList<>();
//		List<Criteria> subCriteria2 = new ArrayList<>();
//		List<Criteria> subCriteria3 = new ArrayList<>();
//		Object[] keys = andQuery.keySet().toArray();
//		Object[] subKeys = orQuery.keySet().toArray();
//		for (int x = 0; x < andQuery.size(); x++) {
//			Object[] valuesAnd = andQuery.values().toArray();
//			if (valuesAnd[x].toString().contains(",")) {
//				String[] valueOr = valuesAnd[x].toString().split(",");
//				for (String k : valueOr) {
//					criteria1.add(Criteria.where((String) keys[x]).is(k));
//				}
//			} else {
//				criteria1.add(Criteria.where((String) keys[x]).is(andQuery.get(keys[x])));
//			}
//		}
//		for (int x = 0; x < orQuery.size(); x++) {
//			Object[] valuesOr = orQuery.values().toArray();
//			if (subKeys[x].toString().contains("projectManagerShortName") || subKeys[x].toString().contains("projectCoManagerShortName")) {
//				if (valuesOr[x].toString().contains(",")) {
//					String[] valueOr = valuesOr[x].toString().split(",");
//					for (String k : valueOr) {
//						subCriteria1.add(Criteria.where((String) subKeys[x]).is(k));
//					}
//				} else {
//					subCriteria1.add(Criteria.where((String) subKeys[x]).is(orQuery.get(subKeys[x])));
//				}
//			}
//			if (subKeys[x].toString().contains("projectName")) {
//			//	subCriteria1.add(Criteria.where((String) subKeys[x]).is(orQuery.get(subKeys[x])));
//				subCriteria3.add(Criteria.where((String) subKeys[x]).regex((String) valuesOr[x] ,"i"));
//			}
//			if (subKeys[x].toString().contains("projectType")) {
//				if (valuesOr[x].toString().contains(",")) {
//					String[] valueOr = valuesOr[x].toString().split(",");
//					for (String k : valueOr) {
//						subCriteria2.add(Criteria.where((String) subKeys[x]).is(k));
//					}
//				} else {
//					subCriteria2.add(Criteria.where((String) subKeys[x]).is(orQuery.get(subKeys[x])));
//				}
//			}
//
//		}
//
//		Query query = new Query();
//		query.with(pageable);
//		if(!subCriteria1.isEmpty()) {
//			criteria2.add(new Criteria().orOperator(subCriteria1));
//		}
//		if(!subCriteria2.isEmpty()) {
//			criteria2.add(new Criteria().orOperator(subCriteria2));
//		}
//		if(!subCriteria3.isEmpty()) {
//			criteria2.add(new Criteria().orOperator(subCriteria3));
//		}
//		if(!criteria1.isEmpty()) {
//			criteria2.add(new Criteria().andOperator(criteria1));
//		}
//		if(!criteria2.isEmpty()) {
//			criteria.add(new Criteria().andOperator(criteria2));
//		}
//		if (!criteria.isEmpty()) {
//			query.addCriteria(new Criteria().orOperator(criteria));
//		}
//		List<T> items = (List<T>) mongoTemplate.find(query, entityClass);
//		Long count = mongoTemplate.count(query.skip(0).limit(0), entityClass);
//		return new PageImpl<>(items, pageable, count);
//	}
//	
//	public <T> Page<T> filterDocumentAndSubDocumentPagingBySkill(Map<String, ? extends Object> andQuery, Map<String, ? extends Object> orQuery, Pageable pageable,
//			Class<?> entityClass, String collectionName) {
//		List<Criteria> criteria = new ArrayList<>();
//		List<Criteria> subCriteria = new ArrayList<>();
//		Object[] keys = andQuery.keySet().toArray();
//		Object[] subKeys = orQuery.keySet().toArray();
//		for (int x = 0; x < orQuery.size(); x++) {
//			Object[] values = orQuery.values().toArray();
//			if (values.length > 0) {
//				if (subKeys[x].toString().contains("skillProficiencies.id") || subKeys[x].toString().contains("employeeID")) {
//					if (values[x].toString().contains(",")) {
//						String[] valueOr = values[x].toString().split(",");
//						for (String k : valueOr) {
//							if (values.length > 1) {
//								subCriteria.add(Criteria.where("skillProficiencies")
//	                                    .elemMatch(Criteria.where("id").is(k.trim())
//	                                            .and("yearsOfExp").gte(Double.parseDouble(values[1].toString()))));
//							} else {
//								subCriteria.add(Criteria.where((String) subKeys[x]).is(k.trim()));
//							}
//						}
//					} else {
//						if (values.length > 1) {
//							subCriteria.add(Criteria.where("skillProficiencies")
//	                                .elemMatch(Criteria.where("id").is(((String) values[x]).trim())
//	                                        .and("yearsOfExp").gte(Double.parseDouble(values[1].toString()))));
//						}else {
//							subCriteria.add(Criteria.where((String) subKeys[x]).is(values[x]));
//						}
//						
//					}
//				}
//
//			}
//		}
//		for (int x = 0; x < andQuery.size(); x++) {
//			Object[] values = andQuery.values().toArray();
//			if (values.length > 0) {
//				if (values[x].toString().contains(",")) {
//					String[] valueOr = values[x].toString().split(",");
//					for (String k : valueOr) {
//						criteria.add(Criteria.where((String) keys[x]).is(k));
//					}
//				} else {
//					criteria.add(Criteria.where((String) keys[x]).is(values[x]));
//				}
//			}
//		}
//		Query query = new Query();
//		query.with(pageable);
//		if(!subCriteria.isEmpty()) {
//			criteria.add(new Criteria().orOperator(subCriteria));
//		}
//		if (!criteria.isEmpty()) {
//			query.addCriteria(new Criteria().andOperator(criteria));
//		}
//		List<T> items = (List<T>) mongoTemplate.find(query, entityClass, collectionName);
//		Long count = mongoTemplate.count(query.skip(0).limit(0), entityClass, collectionName);
//		return new PageImpl<>(items, pageable, count);
//	}
//	
//	public <T> Page<T> findDocumentPaging(Map<String, ? extends Object> require, Pageable pageable,
//			Class<?> entityClass) {
//		List<Criteria> andCriteria = new ArrayList<>();
//		List<Criteria> orCriteria = new ArrayList<>();
//		Object[] keys = require.keySet().toArray();
//		for (int x = 0; x < require.size(); x++) {
//			if (keys[x].toString().contains(",")) {
//				String[] keyOr = keys[x].toString().split(",");
//				String keyConvert = require.get(keys[x]).toString().replaceAll("[()]", "\\\\$0");
//				keyConvert = keyConvert.replace("[", "\\\\$0").replace("]", "\\\\$0");
//				keyConvert = keyConvert.replace("{", "\\\\$0").replace("}", "\\\\$0");
//				for (String k : keyOr) {
//					orCriteria.add(Criteria.where(k).regex(keyConvert, "i"));					
//				}
//			} else {
//				andCriteria.add(Criteria.where((String) keys[x]).is(require.get(keys[x])));				
//			}
//		}
//		Query query = new Query();
//		query.with(pageable);
//		Criteria matchCriteria = new Criteria();
//		if (!orCriteria.isEmpty()) {
//			matchCriteria.orOperator(orCriteria.toArray(new Criteria[0]));
//		}
//		if (!andCriteria.isEmpty()) {
//			matchCriteria.andOperator(andCriteria.toArray(new Criteria[0]));
//		}
//		query.addCriteria(matchCriteria);
//		List<T> items = (List<T>) mongoTemplate.find(query, entityClass);
//		Long count = mongoTemplate.count(query.skip(0).limit(0), entityClass);
//		return new PageImpl<>(items, pageable, count);
//	}
//	
//	public <T> Page<T> findDocumentPagingAllStt(Map<String, ? extends Object> require, Pageable pageable,
//			Class<?> entityClass, String collectionName) {
//		List<Criteria> andCriteria = new ArrayList<>();
//		List<Criteria> orCriteria = new ArrayList<>();
//		Object[] keys = require.keySet().toArray();
//		Object[] values = require.values().toArray();
//		for (int x = 0; x < require.size(); x++) {
//			if (keys[x].toString().contains(",")) {
//				String[] keyOr = keys[x].toString().split(",");
//				String keyConvert = require.get(keys[x]).toString().replaceAll("[()]", "\\\\$0");
//				keyConvert = keyConvert.replace("[", "\\\\$0").replace("]", "\\\\$0");
//				keyConvert = keyConvert.replace("{", "\\\\$0").replace("}", "\\\\$0");
//				for (String k : keyOr) {
//					orCriteria.add(Criteria.where(k).regex(keyConvert, "i"));					
//				}
//			} else if (values[x].toString().contains(",")){
//				String[] keyOr = values[x].toString().split(",");
//				String keyConvert = require.get(keys[x]).toString().replaceAll("[()]", "\\\\$0");
//				keyConvert = keyConvert.replace("[", "\\\\$0").replace("]", "\\\\$0");
//				keyConvert = keyConvert.replace("{", "\\\\$0").replace("}", "\\\\$0");
//				for (String k : keyOr) {
//					orCriteria.add(Criteria.where((String) keys[x]).is(k));						
//				}
//			} else {
//				andCriteria.add(Criteria.where((String) keys[x]).is(require.get(keys[x])));	
//			}
//		}
//		Query query = new Query();
//		query.with(pageable);
//
//		Criteria matchCriteria = new Criteria();
//		if (!orCriteria.isEmpty()) {
//		    matchCriteria.orOperator(orCriteria.toArray(new Criteria[0]));
//		}
//		if (!andCriteria.isEmpty()) {
//			matchCriteria.andOperator(andCriteria.toArray(new Criteria[0]));
//		}
//		
//		query.addCriteria(matchCriteria);
//		List<T> items = (List<T>) mongoTemplate.find(query, entityClass, collectionName);
//		Long count = mongoTemplate.count(query.skip(0).limit(0), entityClass, collectionName);
//		return new PageImpl<>(items, pageable, count);
//	}
//	
//	public <T> Page<T> filterDocumentPagingAllStt(Map<String, ? extends Object> require, Pageable pageable,
//			Class<?> entityClass, String collectionName) {
//		List<Criteria> andCriteria = new ArrayList<>();
//		List<Criteria> orCriteria = new ArrayList<>();
//		Object[] keys = require.keySet().toArray();
//		Object[] values = require.values().toArray();
//		for (int x = 0; x < require.size(); x++) {
//			if (keys[x].toString().contains(",")) {
//				String[] keyOr = keys[x].toString().split(",");
//				String keyConvert = require.get(keys[x]).toString().replaceAll("[()]", "\\\\$0");
//				keyConvert = keyConvert.replace("[", "\\\\$0").replace("]", "\\\\$0");
//				keyConvert = keyConvert.replace("{", "\\\\$0").replace("}", "\\\\$0");
//				for (String k : keyOr) {
//					orCriteria.add(Criteria.where(k).regex(keyConvert, "i"));					
//				}
//			} else if (values[x].toString().contains(",")){
//				String[] keyOr = values[x].toString().split(",");
//				String keyConvert = require.get(keys[x]).toString().replaceAll("[()]", "\\\\$0");
//				keyConvert = keyConvert.replace("[", "\\\\$0").replace("]", "\\\\$0");
//				keyConvert = keyConvert.replace("{", "\\\\$0").replace("}", "\\\\$0");
//				List<Criteria> valueOrCriteria = new ArrayList<>();
//				for (String k : keyOr) {
//					valueOrCriteria.add(Criteria.where((String) keys[x]).is(k));						
//				}
//				orCriteria.add(new Criteria().orOperator(valueOrCriteria.toArray(new Criteria[0])));
//			} else {
//				andCriteria.add(Criteria.where((String) keys[x]).is(require.get(keys[x])));	
//			}
//		}
//		
//		List<Criteria> combinedCriteria = new ArrayList<>();
//
//		for (Criteria orCriterion : orCriteria) {
//		    combinedCriteria.add(orCriterion);
//		}
//
//		for (Criteria andCriterion : andCriteria) {
//		    combinedCriteria.add(andCriterion);
//		}
//		Criteria matchCriteria = new Criteria().andOperator(combinedCriteria.toArray(new Criteria[0]));
//		
//		Query query = new Query();
//		query.with(pageable);
//		query.addCriteria(matchCriteria);
//		
//		List<T> items = (List<T>) mongoTemplate.find(query, entityClass, collectionName);
//		Long count = mongoTemplate.count(query.skip(0).limit(0), entityClass, collectionName);
//		return new PageImpl<>(items, pageable, count);
//	}
//	
//	public <T> Page<T> findDocumentPaging(Map<String, ? extends Object> require, Pageable pageable,
//			Class<?> entityClass, String collectionName) {
//		List<Criteria> andCriteria = new ArrayList<>();
//		List<Criteria> orCriteria = new ArrayList<>();
//		Object[] keys = require.keySet().toArray();
//		for (int x = 0; x < require.size(); x++) {
//			if (keys[x].toString().contains(",")) {
//				String[] keyOr = keys[x].toString().split(",");
//				String keyConvert = require.get(keys[x]).toString().replaceAll("[()]", "\\\\$0");
//				keyConvert = keyConvert.replace("[", "\\\\$0").replace("]", "\\\\$0");
//				keyConvert = keyConvert.replace("{", "\\\\$0").replace("}", "\\\\$0");
//				for (String k : keyOr) {
//					orCriteria.add(Criteria.where(k).regex(keyConvert, "i"));					
//				}
//			} else {
//				andCriteria.add(Criteria.where((String) keys[x]).is(require.get(keys[x])));				
//			}
//		}
//		Query query = new Query();
//		query.with(pageable);
//		Criteria matchCriteria = new Criteria();
//		if (!orCriteria.isEmpty()) {
//			matchCriteria.orOperator(orCriteria.toArray(new Criteria[0]));
//		}
//		if (!andCriteria.isEmpty()) {
//			matchCriteria.andOperator(andCriteria.toArray(new Criteria[0]));
//		}
//		query.addCriteria(matchCriteria);
//		List<T> items = (List<T>) mongoTemplate.find(query, entityClass, collectionName);
//		Long count = mongoTemplate.count(query.skip(0).limit(0), entityClass, collectionName);
//		return new PageImpl<>(items, pageable, count);
//	}
//
//	public <T> List<T> filterDocuments(Map<String, ? extends Object> require, Class<?> entityClass) {
//		Query query = new Query();
//		String sortItem = (String) require.get("sort");
//		if (StringUtils.isNotBlank(sortItem)) {
//			require.remove("sort");
//			List<Order> orders = new ArrayList<>();
//			orders.add(sortItem.split(",")[1].equalsIgnoreCase(Sort.Direction.ASC.name()) ? Order.asc(sortItem.split(",")[0]) : Order.desc(sortItem.split(",")[0]));
//			query.with(Sort.by(orders));
//		}
//		List<Criteria> criteria = new ArrayList<>();
//		Object[] keys = require.keySet().toArray();
//		for (int x = 0; x < require.size(); x++) {
//			criteria.add(Criteria.where((String) keys[x]).is(require.get(keys[x])));
//		}
//		if (!criteria.isEmpty()) {
//			query.addCriteria(new Criteria().andOperator(criteria));
//		}
//		return (List<T>) mongoTemplate.find(query, entityClass);
//	}
//	
//	public <T> List<T> filterDocumentsBySkill(Map<String, ? extends Object> require, Class<?> entityClass) {
//		Query query = new Query();
//		String sortItem = (String) require.get("sort");
//		if (StringUtils.isNotBlank(sortItem)) {
//			require.remove("sort");
//			List<Order> orders = new ArrayList<>();
//			orders.add(sortItem.split(",")[1].equalsIgnoreCase(Sort.Direction.ASC.name()) ? Order.asc(sortItem.split(",")[0]) : Order.desc(sortItem.split(",")[0]));
//			query.with(Sort.by(orders));
//		}
//		List<Criteria> criteria = new ArrayList<>();
//		Object[] keys = require.keySet().toArray();
//		Object[] values = require.values().toArray();
//		for (int x = 0; x < require.size(); x++) {
//			String[] valueOr = values[x].toString().split(",");
//			for (String k : valueOr) {
//				criteria.add(Criteria.where((String) keys[x]).regex((String)k, "i"));
//			}
//		}
//		if (!criteria.isEmpty()) {
//			query.addCriteria(new Criteria().andOperator(criteria));
//		}
//		return (List<T>) mongoTemplate.find(query, entityClass);
//	}
//
//	public <T> Page<T> searchDocumentPaging(Map<String, String> require, Pageable pageable, Class<?> entityClass) {
//		List<Criteria> criteria = new ArrayList<>();
//		Object[] keys = require.keySet().toArray();
//		for (int x = 0; x < require.size(); x++) {
//			String keyConvert = require.get(keys[x]).replaceAll("[()]", "\\\\$0");
//			keyConvert = keyConvert.replace("[", "\\\\$0").replace("]", "\\\\$0");
//			keyConvert = keyConvert.replace("{", "\\\\$0").replace("}", "\\\\$0");
//			criteria.add(Criteria.where((String) keys[x]).regex(keyConvert, "i"));
//		}
//		Query query = new Query();
//		query.with(pageable);
//		if (!criteria.isEmpty()) {
//			query.addCriteria(new Criteria().orOperator(criteria));
//		}
//		List<T> items = (List<T>) mongoTemplate.find(query, entityClass);
//		Long count = mongoTemplate.count(query.skip(0).limit(0), entityClass);
//		return new PageImpl<>(items, pageable, count);
//	}
//	
//	public <T> Page<T> searchDocumentPagingWithAndCondition(Map<String, String> require, Pageable pageable, Class<?> entityClass) {
//		List<Criteria> criteria = new ArrayList<>();
//		Object[] keys = require.keySet().toArray();
//		for (int x = 0; x < require.size(); x++) {
//			String keyConvert = require.get(keys[x]).replaceAll("[()]", "\\\\$0");
//			keyConvert = keyConvert.replace("[", "\\\\$0").replace("]", "\\\\$0");
//			keyConvert = keyConvert.replace("{", "\\\\$0").replace("}", "\\\\$0");
//			criteria.add(Criteria.where((String) keys[x]).regex(keyConvert, "i"));
//		}
//		Query query = new Query();
//		query.with(pageable);
//		if (!criteria.isEmpty()) {
//			query.addCriteria(new Criteria().andOperator(criteria));
//		}
//		List<T> items = (List<T>) mongoTemplate.find(query, entityClass);
//		Long count = mongoTemplate.count(query.skip(0).limit(0), entityClass);
//		return new PageImpl<>(items, pageable, count);
//	}
//	
//	public <T> Page<T> searchDocumentPaging(Map<String, String> require, Pageable pageable, Class<?> entityClass, String collectionName) {
//		List<Criteria> andCriteria = new ArrayList<>();
//		List<Criteria> orCriteria = new ArrayList<>();
//		Object[] keys = require.keySet().toArray();
//		for (int x = 0; x < require.size(); x++) {
//			if ((!keys[x].toString().contains("peopleManagerShortName") && !keys[x].toString().contains("status"))
//					|| "com.dxc.dto.PlatformResourceDto".equalsIgnoreCase(entityClass.getName())) {
//				String[] keyOr = keys[x].toString().split(",");
//				String keyConvert = require.get(keys[x]).toString().replaceAll("[()]", "\\\\$0");
//				keyConvert = keyConvert.replace("[", "\\\\$0").replace("]", "\\\\$0");
//				keyConvert = keyConvert.replace("{", "\\\\$0").replace("}", "\\\\$0");
//				for (String k : keyOr) {
//					orCriteria.add(Criteria.where(k).regex(keyConvert, "i"));
//				}
//			} else {
//				andCriteria.add(Criteria.where((String) keys[x]).is(require.get(keys[x])));
//			}
//		}
//		Query query = new Query();
//		query.with(pageable);
//		Criteria matchCriteria = new Criteria();
//		if (!orCriteria.isEmpty()) {
//			matchCriteria.orOperator(orCriteria.toArray(new Criteria[0]));
//		}
//		if (!andCriteria.isEmpty()) {
//			matchCriteria.andOperator(andCriteria.toArray(new Criteria[0]));
//		}
//		query.addCriteria(matchCriteria);
//		List<T> items = (List<T>) mongoTemplate.find(query, entityClass, collectionName);
//		Long count = mongoTemplate.count(query.skip(0).limit(0), entityClass, collectionName);
//		return new PageImpl<>(items, pageable, count);
//	}
//	public Long count(Query query,Class<?> entityClass, String collectionName) {
//		 return mongoTemplate.count(query.skip(0).limit(0), entityClass, collectionName);
//	}
//
//	public <T> Page<T> searchDocumentListPagingByQuery(Query query, Pageable pageable, Class<?> entityClass, String collectionName) {
//		List<T> items = (List<T>) mongoTemplate.find(query, entityClass, collectionName);
//		Long count = mongoTemplate.count(query.skip(0).limit(0), entityClass, collectionName);
//		return new PageImpl<>(items, pageable, count);
//	}
//	
//	public <T> List<T> searchDocumentListByQuery(Query query, Class<?> entityClass, String collectionName) {
//		List<T> items = (List<T>) mongoTemplate.find(query, entityClass, collectionName);
//		Long count = mongoTemplate.count(query.skip(0).limit(0), entityClass, collectionName);
//		return items;
//	}
//	public <T> List<T> searchDocumentList(Map<String, String> require, Class<?> entityClass, String collectionName) {
//		List<Criteria> andCriteria = new ArrayList<>();
//		List<Criteria> orCriteria = new ArrayList<>();
//		Object[] keys = require.keySet().toArray();
//		for (int x = 0; x < require.size(); x++) {
//			if ((!keys[x].toString().contains("peopleManagerShortName") && !keys[x].toString().contains("status"))
//					|| "com.dxc.dto.PlatformResourceDto".equalsIgnoreCase(entityClass.getName())) {
//				String[] keyOr = keys[x].toString().split(",");
//				String keyConvert = require.get(keys[x]).toString().replaceAll("[()]", "\\\\$0");
//				keyConvert = keyConvert.replace("[", "\\\\$0").replace("]", "\\\\$0");
//				keyConvert = keyConvert.replace("{", "\\\\$0").replace("}", "\\\\$0");
//				for (String k : keyOr) {
//					orCriteria.add(Criteria.where(k).regex(keyConvert, "i"));
//				}
//			} else {
//				andCriteria.add(Criteria.where((String) keys[x]).is(require.get(keys[x])));
//			}
//		}
//		Query query = new Query();
//		Criteria matchCriteria = new Criteria();
//		if (!orCriteria.isEmpty()) {
//			matchCriteria.orOperator(orCriteria.toArray(new Criteria[0]));
//		}
//		if (!andCriteria.isEmpty()) {
//			matchCriteria.andOperator(andCriteria.toArray(new Criteria[0]));
//		}
//		query.addCriteria(matchCriteria);
//		List<T> items = (List<T>) mongoTemplate.find(query, entityClass, collectionName);
//		return items;
//	}
//	
//
//	/* 
// 	Query for function findDocumentPagingSubordinate with "ACTIVE" status:
//	  {
//		  aggregate: "__collection__",
//		  pipeline: [
//		    { $match: { shortName: "hvu7" } },
//		    {
//		      $graphLookup: {
//		        from: "resources",
//		        startWith: "$shortName",
//		        connectFromField: "shortName",
//		        connectToField: "peopleManagerShortName",
//		        as: "descendants",
//		        maxDepth: 10,
//		        restrictSearchWithMatch: {
//		          status: "ACTIVE",
//		        },
//		      },
//		    },
//		    { $unwind: "$descendants" },
//		    { $sort: { "descendants.employeeID": 1 } },
//		    {
//		      $group: {
//		        _id: "$_id",
//		        descendants: { $push: "$descendants" },
//		      },
//		    },
//		    {
//		      $project: {
//		        descendants: {
//		          $slice: ["$descendants", 0, 10],
//		        },
//		        count: { $size: "$descendants" },
//		      },
//		    },
//		  ],
//		} */
//	
//	public <T> Page<EmployeeSubordinate> findDocumentPagingSubordinate(Map<String, String> require, Pageable pageable, Class<?> entityClass, String collectionName, Long maxDepth) {
//	Object[] keys = require.keySet().toArray();
//		Object[] values = require.values().toArray();
//		String peopleManagerShortName = null;
//		String sortDir = "asc"; // default
//		String sortCl = null; // default
//		for (int i = 0; i < require.size(); i++) {
//			if (keys[i].equals("peopleManagerShortName")) {
//				peopleManagerShortName = values[i].toString();
//			}
//			if (keys[i].equals("sort")) {
//				if (values[i].toString().contains(",")) {
//					String[] valueOr = values[i].toString().split(",");
//					sortCl = valueOr[0].toString();
//					sortDir = valueOr[1].toString();
////					for (String k : valueOr) {
////						if (k.equals("desc")) {
////							sortDir = "desc";
////						}				
////					}
//				}	
//			}
//		}
//		List<EmployeeSubordinate> results = new ArrayList<>();
//		for (int x = 0; x < require.size(); x++) {
//			final Criteria byShortName = new Criteria("peopleManagerShortName").is(peopleManagerShortName);
//			final Criteria bypPeopleManagerShortName = new Criteria("shortName").is(byShortName.getCriteriaObject().values().toString().replace("[", "").replace("]", ""));
//			final MatchOperation matchStage = Aggregation.match(bypPeopleManagerShortName);
//			final SortOperation sort = (sortDir.equals("asc")) ? Aggregation.sort(Sort.by(Sort.Order.asc("descendants."+sortCl))) : Aggregation.sort(Sort.by(Sort.Order.desc("descendants."+sortCl)));
//			if (values[x].equals("ACTIVE")) {
//				GraphLookupOperation graphLookupOperation = GraphLookupOperation.builder()
//						.from("resources")
//						.startWith("$shortName")
//						.connectFrom("shortName")
//						.connectTo("peopleManagerShortName")
//						.restrict(new Criteria("status").is("ACTIVE"))
//						.maxDepth(maxDepth != null ? maxDepth : MAX_DEPTH_SUPPORTED)
//						.as("descendants");
//				final ProjectionOperation project = Aggregation.project()
//				        .and("descendants").project("slice",  pageable.getPageNumber()*pageable.getPageSize(), 10).as("descendants")
//				        .and(ArrayOperators.Size.lengthOfArray("$descendants")).as("count");
//				Aggregation aggregation = Aggregation.newAggregation(matchStage, graphLookupOperation,
//						Aggregation.unwind("$descendants"),
//						sort,
//						Aggregation.group("_id").push("descendants").as("descendants"),
//						project);
//				results = mongoTemplate.aggregate(aggregation, "resources", EmployeeSubordinate.class).getMappedResults();
//			} else if (values[x].equals("ALL")) {
//				GraphLookupOperation graphLookupOperation = GraphLookupOperation.builder()
//						.from("resources")
//						.startWith("$shortName")
//						.connectFrom("shortName")
//						.connectTo("peopleManagerShortName")
//						.maxDepth(maxDepth != null ? maxDepth : MAX_DEPTH_SUPPORTED)
//						.as("descendants");
//				final ProjectionOperation project = Aggregation.project()
//				        .and("descendants").project("slice",  pageable.getPageNumber()*pageable.getPageSize(), 10).as("descendants")
//				        .and(ArrayOperators.Size.lengthOfArray("$descendants")).as("count");
//				Aggregation aggregation = Aggregation.newAggregation(matchStage, graphLookupOperation,
//						Aggregation.unwind("$descendants"),
//						sort,
//						Aggregation.group("_id").push("descendants").as("descendants"),
//						project);
//				results = mongoTemplate.aggregate(aggregation, "resources", EmployeeSubordinate.class).getMappedResults();
//			}
//		}
//		Long count = 0L;
//		if (results.size() > 0 && !results.isEmpty()) {
//			if (results.get(0).getDescendants().size() > 0 && !results.get(0).getDescendants().isEmpty()) {
//				count = results.get(0).getCount();
//			}
//		}
//		return new PageImpl<>(results, pageable, count);
//	}
//	/* 
// 	Query for function searchDocumentPagingSubordinate with "ACTIVE" status, text search :"llai21":
//	{
//		  aggregate: "__collection__",
//		  pipeline: [
//		    { $match: { shortName: "hvu7" } },
//		    {
//		      $graphLookup: {
//		        from: "resources",
//		        startWith: "$shortName",
//		        connectFromField: "shortName",
//		        connectToField: "peopleManagerShortName",
//		        as: "descendants",
//		        maxDepth: 10,
//		        restrictSearchWithMatch: {
//		          status: "ACTIVE",
//		        },
//		      },
//		    },
//		    { $unwind: "$descendants" },
//		    { $sort: { "descendants.employeeID": 1 } },
//		    {
//		      $match: {
//		        $or: [
//		          {
//		            "descendants.lastName": {
//		              $regularExpression: {
//		                pattern: "llai21",
//		                options: "i",
//		              },
//		            },
//		          },
//		          {
//		            "descendants.fullName": {
//		              $regularExpression: {
//		                pattern: "llai21",
//		                options: "i",
//		              },
//		            },
//		          },
//		          {
//		            "descendants.middleName": {
//		              $regularExpression: {
//		                pattern: "llai21",
//		                options: "i",
//		              },
//		            },
//		          },
//		          {
//		            "descendants.shortName": {
//		              $regularExpression: {
//		                pattern: "llai21",
//		                options: "i",
//		              },
//		            },
//		          },
//		        ],
//		      },
//		    },
//		    {
//		      $group: {
//		        _id: "$_id",
//		        descendants: { $push: "$descendants" },
//		      },
//		    },
//		    {
//		      $project: {
//		        descendants: {
//		          $slice: ["$descendants", 0, 10],
//		        },
//		        count: { $size: "$descendants" },
//		      },
//		    },
//		  ],
//		}
//	*/
//	
//	public <T> Page<EmployeeSubordinate> searchDocumentPagingSubordinate(Map<String, String> require, Pageable pageable, Class<?> entityClass, String collectionName, Long maxDepth) {
//	Object[] keys = require.keySet().toArray();
//		Object[] values = require.values().toArray();
//		String peopleManagerShortName = null;
//		String status = null;
//		String sortDir = "asc"; // default
//		Double yoeValue = Double.parseDouble(require.get("skillProficiencies.yearsOfExp") == null ? "0"
//				: require.get("skillProficiencies.yearsOfExp"));
//		require.remove("skillProficiencies.yearsOfExp");
//		List<Criteria> criteria = new ArrayList<>();
//		for (int x = 0; x < require.size(); x++) {
//			if ((!keys[x].toString().contains("peopleManagerShortName") && !keys[x].toString().contains("status") && !keys[x].toString().contains("sort"))) {
//				String keyConvert = require.get(keys[x]).toString().replaceAll("[()]", "\\\\$0");
//				keyConvert = keyConvert.replace("[", "\\\\$0").replace("]", "\\\\$0");
//				keyConvert = keyConvert.replace("{", "\\\\$0").replace("}", "\\\\$0");
//				String[] valueOr = values[x].toString().split(",");
//				if (keys[x].toString().contains("listSkillID")) {
//					for (String k : valueOr) {
//						if(yoeValue != null && yoeValue > 0) {
//							criteria.add(Criteria.where("descendants.skillProficiencies")
//		                            .elemMatch(Criteria.where("id").is(k.trim())
//		                                    .and("yearsOfExp").gte(yoeValue)));
//							
//						}else {
//							criteria.add(Criteria.where("descendants.skillProficiencies.id").is(k));
//						}
//					}
//				} else {
//					for (String k : valueOr) {
//						criteria.add(Criteria.where("descendants." + (String) keys[x]).regex((String)k, "i"));
//					}
//				}
//			} else {
//				if (keys[x].equals("peopleManagerShortName")) {
//					peopleManagerShortName = values[x].toString();
//				}
//				else if (keys[x].equals("status")) {
//					status = values[x].toString();
//				}
//				else if (keys[x].equals("sort")) {
//					if (values[x].toString().contains(",")) {
//						String[] valueOr = values[x].toString().split(",");
//						for (String k : valueOr) {
//							if (k.equals("desc")) {
//								sortDir = "desc";
//							}				
//						}
//					}
//				}
//			}
//
//		}
//		Criteria matchCriteria = new Criteria();
//		if (!criteria.isEmpty()) {
//			matchCriteria.orOperator(criteria.toArray(new Criteria[0]));
//		}
//		List<EmployeeSubordinate> results = new ArrayList<>();
//		final Criteria byShortName = new Criteria("peopleManagerShortName").is(peopleManagerShortName);
//		final Criteria bypPeopleManagerShortName = new Criteria("shortName").is(byShortName.getCriteriaObject().values().toString().replace("[", "").replace("]", ""));
//		final MatchOperation matchStage = Aggregation.match(bypPeopleManagerShortName);
//		final MatchOperation matchStage1 = Aggregation.match(matchCriteria);
//		final SortOperation sort = (sortDir == "asc") ? Aggregation.sort(Sort.by(Sort.Order.asc("descendants.employeeID"))) : Aggregation.sort(Sort.by(Sort.Order.desc("descendants.employeeID")));
//		if (status.equals("ACTIVE")) {
//			GraphLookupOperation graphLookupOperation = GraphLookupOperation.builder()
//					.from("resources")
//					.startWith("$shortName")
//					.connectFrom("shortName")
//					.connectTo("peopleManagerShortName")
//					.restrict(new Criteria("status").is("ACTIVE"))
//					.maxDepth(maxDepth != null ? maxDepth : MAX_DEPTH_SUPPORTED)
//					.as("descendants");
//			final ProjectionOperation project = Aggregation.project()
//			        .and("descendants").project("slice",  pageable.getPageNumber()*pageable.getPageSize(), 10).as("descendants")
//			        .and(ArrayOperators.Size.lengthOfArray("$descendants")).as("count");
//			Aggregation aggregation = Aggregation.newAggregation(matchStage, graphLookupOperation,
//					Aggregation.unwind("$descendants"),
//					sort,
//					matchStage1,
//					Aggregation.group("_id").push("descendants").as("descendants"),
//					project);
//			results = mongoTemplate.aggregate(aggregation, "resources", EmployeeSubordinate.class).getMappedResults();
//		} else if (status.equals("ALL")) {
//			GraphLookupOperation graphLookupOperation = GraphLookupOperation.builder()
//					.from("resources")
//					.startWith("$shortName")
//					.connectFrom("shortName")
//					.connectTo("peopleManagerShortName")
//					.maxDepth(maxDepth != null ? maxDepth : MAX_DEPTH_SUPPORTED)
//					.as("descendants");
//			final ProjectionOperation project = Aggregation.project()
//			        .and("descendants").project("slice",  pageable.getPageNumber()*pageable.getPageSize(), 10).as("descendants")
//			        .and(ArrayOperators.Size.lengthOfArray("$descendants")).as("count");
//			Aggregation aggregation = Aggregation.newAggregation(matchStage, graphLookupOperation,
//					Aggregation.unwind("$descendants"),
//					sort,
//					matchStage1,
//					Aggregation.group("_id").push("descendants").as("descendants"),
//					project);
//			results = mongoTemplate.aggregate(aggregation, "resources", EmployeeSubordinate.class).getMappedResults();
//		}
//	
//		Long count = 0L;
//		if (results.size() > 0 && !results.isEmpty()) {
//			if (results.get(0).getDescendants().size() > 0 && !results.get(0).getDescendants().isEmpty()) {
//				count = results.get(0).getCount();
//			}
//		}
//		return new PageImpl<>(results, pageable, count);
//	}
//	
//	public List <ProjectClientName> getDistinct(String key, String collection) {
//		String a = null;
//		Aggregation aggregation = Aggregation.newAggregation(
//				Aggregation.group(key));
//		return (List<ProjectClientName>) mongoTemplate.aggregate(aggregation, collection, ProjectClientName.class).getMappedResults();
//	}
//	
//	public <T> Page<T> getAll(Pageable pageable, Class<?> docClass) {
//		Query query = new Query();
//		query.with(pageable);
//		return (Page<T>) PageableExecutionUtils.getPage(mongoTemplate.find(query, docClass), pageable,
//				() -> mongoTemplate.count(query.skip(0).limit(0), docClass));
//	}
//
//	public <T> List<T> findByKeys(HashMap<String, String> require, Class<?> entityClass) {
//		List<Criteria> criteria = new ArrayList<>();
//		Object[] keys = require.keySet().toArray();
//		for (int x = 0; x < require.size(); x++) {
//			criteria.add(Criteria.where((String) keys[x]).is(require.get(keys[x])));
//		}
//		Query query = new Query();
//		query.addCriteria(new Criteria().andOperator(criteria));
//		return (List<T>) mongoTemplate.find(query, entityClass);
//	}
//	
//	public <T> List<T> findByKeys(HashMap<String, String> require, Class<?> entityClass, String collectionName) {
//		Query query = new Query();
//		if (!require.isEmpty()) {
//			List<Criteria> criteria = new ArrayList<>();
//			Object[] keys = require.keySet().toArray();
//			for (int x = 0; x < require.size(); x++) {
//				criteria.add(Criteria.where((String) keys[x]).is(require.get(keys[x])));
//			}
//			query.addCriteria(new Criteria().andOperator(criteria));
//		}
//		return (List<T>) mongoTemplate.find(query, entityClass, collectionName);
//	}
//
//	public <T> List<T> findByDataRelative(HashMap<String, String> require, Class<?> entityClass) {
//		List<Criteria> criteria = new ArrayList<>();
//		for (Entry<String, String> keyValue : require.entrySet()) {
//			criteria.add(Criteria.where(keyValue.getKey()).regex(keyValue.getValue()));
//		}
//		Query query = new Query();
//		query.addCriteria(new Criteria().orOperator(criteria));
//		return (List<T>) mongoTemplate.find(query, entityClass);
//	}
//	
//	public <T> List<T> findValueByFieldInput(HashMap<String, String> require, String field, Class<?> entityClass) {
//	    List<Criteria> criteria = new ArrayList<>();
//	    for (Entry<String, String> keyValue : require.entrySet()) {
//	        criteria.add(Criteria.where(keyValue.getKey()).regex(keyValue.getValue()));
//	    }
//	    Query query = new Query();
//	    query.addCriteria(new Criteria().orOperator(criteria));
//
//	    query.fields().include(field);
//
//	    return (List<T>) mongoTemplate.find(query, entityClass);
//	}
//
//
//	public <T> List<T> findByDate(Map<String, String> require, Class<?> entityClass) throws ParseException {
//		Query query = new Query();
//		DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
//		if (require.containsKey("startDate") && !require.containsKey("endDate")) {
//			query.addCriteria(Criteria.where("startDate").is(format.parse(require.get("startDate"))));
//
//		} else if (!require.containsKey("startDate") && require.containsKey("endDate")) {
//			query.addCriteria(Criteria.where("endDate").is(format.parse(require.get("endDate"))));
//		} else {
//			query.addCriteria(Criteria.where("startDate").gte(format.parse(require.get("startDate"))).and("endDate")
//					.lte(format.parse(require.get("endDate"))));
//		}
//
//		return (List<T>) mongoTemplate.find(query, entityClass);
//	}
//
//	public <T> Page<T> filterDocumentPaging(HashMap<String, Object> filterFields, Pageable pageable,
//			Class<?> entityClass) throws ParseException {
//		DateFormat format = new SimpleDateFormat(MongoConstant.DATE_FORMAT);
//		var query = new Query();
//		query.with(pageable);
//		for (Entry<String, ? extends Object> entry : filterFields.entrySet()) {
//			if (!(entry.getKey().equalsIgnoreCase("startDate") || entry.getKey().equalsIgnoreCase("endDate"))) {
//				if (entry.getValue() instanceof Boolean) {
//					query.addCriteria(Criteria.where(entry.getKey()).is(entry.getValue()));
//				} else {
//					List<String> matchValues = new ArrayList<>(
//							Arrays.asList(((String) entry.getValue()).split("\\s*,\\s*")));
//					query.addCriteria(Criteria.where(entry.getKey()).in(matchValues));
//				}
//			} else if (!(filterFields.containsKey("startDate") && filterFields.containsKey("endDate"))) {
//				List<String> keys = new ArrayList<>(Arrays.asList(((String) entry.getValue()).split("-")));
//				if (keys.size() == 2) {
//					LocalDate startDate = LocalDate.of(Integer.valueOf(keys.get(0)), Integer.valueOf(keys.get(1)), 1)
//							.with(TemporalAdjusters.firstDayOfMonth());
//					LocalDate endDate = startDate.with(TemporalAdjusters.lastDayOfMonth());
//					query.addCriteria(Criteria.where(entry.getKey()).gte(startDate)
//							.andOperator(Criteria.where(entry.getKey()).lte(endDate)));
//				} else {
//					query.addCriteria(Criteria.where(entry.getKey()).is(format.parse((String) entry.getValue())));
//				}
//			}
//		}
//		if (filterFields.containsKey("startDate") && filterFields.containsKey("endDate")) {
//			query.addCriteria(Criteria.where("startDate").gte(format.parse((String) filterFields.get("startDate")))
//					.and("endDate").lte(format.parse((String) filterFields.get("endDate"))));
//		}
//		List<T> items = (List<T>) mongoTemplate.find(query, entityClass);
//		Long count = mongoTemplate.count(query.skip(0).limit(0), entityClass);
//		return new PageImpl<>(items, pageable, count);
//	}

	public <T> T saveDocument(T document) {
		return mongoTemplate.save(document);
	}

	public Collection<?> saveAllDocument(List<?> document) {
		return mongoTemplate.insertAll(document);
	}

//	public <T> List<T> findExistingDocuments(Collection<String> documentIds, Class<?> entityClass) {
//		Criteria criteria = Criteria.where(MongoConstant.OBJECT_ID_FIELD).in(documentIds);
//		Query query = new Query(criteria);
//		return (List<T>) mongoTemplate.find(query, entityClass);
//	}
//
//	public <T> List<T> findExistingDocuments(String docName, Collection<String> docValues, Class<?> entityClass) {
//		Criteria criteria = Criteria.where(docName).in(docValues);
//		Query query = new Query(criteria);
//		return (List<T>) mongoTemplate.find(query, entityClass);
//	}
//
	public void updateDocumentById(String documentId, Map<String, ? extends Object> dataToSave, Class<?> entityClass) {
		Criteria criteria = Criteria.where(MongoConstant.OBJECT_ID_FIELD).is(documentId);
		Query query = new Query(criteria);
		Update update = new Update();
		dataToSave.entrySet().forEach(entry -> update.set(entry.getKey(), entry.getValue()));
		mongoTemplate.updateFirst(query, update, entityClass);
	}
//
//	public UpdateResult updateDocuments(Collection<String> documentIds, Map<String, ? extends Object> dataToSave,
//			Class<?> entityClass) {
//		Criteria criteria = Criteria.where(MongoConstant.OBJECT_ID_FIELD).in(documentIds);
//		Query query = new Query(criteria);
//		Update update = new Update();
//		dataToSave.entrySet().forEach(entry -> update.set(entry.getKey(), entry.getValue()));
//		return mongoTemplate.updateMulti(query, update, entityClass);
//	}
//
//	public UpdateResult updateDocumentsByKey(String key, Collection<String> documentIds,
//			Map<String, ? extends Object> dataToSave, Class<?> entityClass) {
//		Criteria criteria = Criteria.where(key).in(documentIds);
//		Query query = new Query(criteria);
//		Update update = new Update();
//		dataToSave.entrySet().forEach(entry -> update.set(entry.getKey(), entry.getValue()));
//		return mongoTemplate.updateMulti(query, update, entityClass);
//	}
//
//	public <T> List<T> findNestedListDocument(String documentId, String nestedDoc, Class<?> entityClass,
//			Class<?> nestedDocClass) {
//		Criteria criteria = Criteria.where(MongoConstant.OBJECT_ID_FIELD).is(documentId);
//		AggregationOperation match = Aggregation.match(criteria);
//		AggregationOperation unwind = Aggregation.unwind(nestedDoc);
//		AggregationOperation replaceRoot = Aggregation.replaceRoot(nestedDoc);
//
//		List<AggregationOperation> allOperations = new ArrayList<>();
//		allOperations.add(match);
//		allOperations.add(unwind);
//		allOperations.add(replaceRoot);
//
//		Aggregation aggregation = Aggregation.newAggregation(allOperations);
//
//		return (List<T>) mongoTemplate
//				.aggregate(aggregation, mongoTemplate.getCollectionName(entityClass), nestedDocClass)
//				.getMappedResults();
//
//	}
//
//	public <T> List<T> findNestedDocumentByCollectionName(String docKey, String docValue, String nestedDoc,
//			String collectionName, Class<?> nestedDocClass) {
//		Criteria criteria = Criteria.where(docKey).is(docValue);
//		AggregationOperation match = Aggregation.match(criteria);
//		AggregationOperation unwind = Aggregation.unwind(nestedDoc);
//		AggregationOperation replaceRoot = Aggregation.replaceRoot(nestedDoc);
//
//		List<AggregationOperation> allOperations = new ArrayList<>();
//		allOperations.add(match);
//		allOperations.add(unwind);
//		allOperations.add(replaceRoot);
//
//		Aggregation aggregation = Aggregation.newAggregation(allOperations);
//
//		return (List<T>) mongoTemplate.aggregate(aggregation, collectionName, nestedDocClass).getMappedResults();
//	}
//	
//	public <T> void createNestedObject(Class<?> docClass, Map<String, String> idMap, T nestedObject,
//			String nestedNode) {
//		Query validationquery = new Query();
//		List<Criteria> criteria = new ArrayList<Criteria>();
//		for (Map.Entry<String, String> entry : idMap.entrySet()) {
//			criteria.add(Criteria.where(entry.getKey()).is(entry.getValue()));
//		}
//		validationquery.addCriteria(new Criteria().andOperator(criteria.toArray(new Criteria[criteria.size()])));
//		Update update = new Update().push(nestedNode, nestedObject);
//		mongoTemplate.updateMulti(validationquery, update, docClass);
//	}
//
//	public <T> T findElementOfNestedArr(String documentId, String nestedId, String nestedDoc, Class<?> entityClass,
//			Class<?> nestedDocClass) {
//
//		AggregationOperation match = Aggregation.match(Criteria.where(MongoConstant.OBJECT_ID_FIELD).is(documentId));
//		AggregationOperation unwind = Aggregation.unwind(nestedDoc);
//		AggregationOperation matchElement = Aggregation
//				.match(Criteria.where(nestedDoc + "." + MongoConstant.OBJECT_ID_FIELD).is(nestedId));
//		AggregationOperation replaceRoot = Aggregation.replaceRoot(nestedDoc);
//
//		List<AggregationOperation> allOperations = new ArrayList<>();
//		allOperations.add(match);
//		allOperations.add(unwind);
//		allOperations.add(matchElement);
//		allOperations.add(replaceRoot);
//
//		Aggregation aggregation = Aggregation.newAggregation(allOperations);
//
//		return (T) mongoTemplate.aggregate(aggregation, mongoTemplate.getCollectionName(entityClass), nestedDocClass)
//				.getUniqueMappedResult();
//
//	}
//
//	public <T> List<T> findNestedDocumentV2(String documentId, String nestedArr, String nestedId, String nestedDoc,
//			Class<?> entityClass, Class<?> nestedDocClass) {
//		Criteria criteria = Criteria.where(MongoConstant.OBJECT_ID_FIELD).is(documentId);
//		AggregationOperation match = Aggregation.match(criteria);
//		AggregationOperation unwind = Aggregation.unwind(nestedArr);
//		AggregationOperation matchElement = Aggregation
//				.match(Criteria.where(nestedArr + "." + MongoConstant.OBJECT_ID_FIELD).is(nestedId));
//
//		AggregationOperation replaceRoot = Aggregation.replaceRoot(nestedArr);
//		AggregationOperation unwindDoc = Aggregation.unwind(nestedDoc);
//		AggregationOperation replaceRootDoc = Aggregation.replaceRoot(nestedDoc);
//
//		List<AggregationOperation> allOperations = new ArrayList<>();
//		allOperations.add(match);
//		allOperations.add(unwind);
//		allOperations.add(matchElement);
//		allOperations.add(replaceRoot);
//		allOperations.add(unwindDoc);
//		allOperations.add(replaceRootDoc);
//
//		Aggregation aggregation = Aggregation.newAggregation(allOperations);
//
//		return (List<T>) mongoTemplate
//				.aggregate(aggregation, mongoTemplate.getCollectionName(entityClass), nestedDocClass)
//				.getMappedResults();
//
//	}
//
//	public <T> T findElementOfNestedArrV2(String documentId, String nestedArr, String nestedId, String nestedDoc,
//			String nestedDocId, Class<?> entityClass, Class<?> nestedDocClass) {
//
//		Criteria criteria = Criteria.where(MongoConstant.OBJECT_ID_FIELD).is(documentId);
//		AggregationOperation match = Aggregation.match(criteria);
//		AggregationOperation unwind = Aggregation.unwind(nestedArr);
//		AggregationOperation matchArr = Aggregation
//				.match(Criteria.where(nestedArr + "." + MongoConstant.OBJECT_ID_FIELD).is(nestedId));
//
//		AggregationOperation replaceRoot = Aggregation.replaceRoot(nestedArr);
//		AggregationOperation unwindDoc = Aggregation.unwind(nestedDoc);
//		AggregationOperation replaceRootDoc = Aggregation.replaceRoot(nestedDoc);
//		AggregationOperation matchElement = Aggregation
//				.match(Criteria.where(nestedDoc + "." + MongoConstant.OBJECT_ID_FIELD).is(nestedDocId));
//
//		List<AggregationOperation> allOperations = new ArrayList<>();
//		allOperations.add(match);
//		allOperations.add(unwind);
//		allOperations.add(matchArr);
//		allOperations.add(replaceRoot);
//		allOperations.add(unwindDoc);
//		allOperations.add(matchElement);
//		allOperations.add(replaceRootDoc);
//
//		Aggregation aggregation = Aggregation.newAggregation(allOperations);
//
//		return (T) mongoTemplate.aggregate(aggregation, mongoTemplate.getCollectionName(entityClass), nestedDocClass)
//				.getUniqueMappedResult();
//
//	}
//
//	public <T> T findNestedDocument(String documentId, String nestedDoc, Class<?> entityClass,
//			Class<?> nestedDocClass) {
//		Criteria criteria = Criteria.where(MongoConstant.OBJECT_ID_FIELD).is(documentId);
//		AggregationOperation match = Aggregation.match(criteria);
//		AggregationOperation unwind = Aggregation.unwind(nestedDoc);
//		AggregationOperation replaceRoot = Aggregation.replaceRoot(nestedDoc);
//
//		List<AggregationOperation> allOperations = new ArrayList<>();
//		allOperations.add(match);
//		allOperations.add(unwind);
//		allOperations.add(replaceRoot);
//
//		Aggregation aggregation = Aggregation.newAggregation(allOperations);
//
//		return (T) mongoTemplate.aggregate(aggregation, mongoTemplate.getCollectionName(entityClass), nestedDocClass)
//				.getUniqueMappedResult();
//	}
//	
//	public <T extends DomainUUID> T findNestedDocument(Class<?> docClass, Map<String, String> idMap, Class<?> nestedDocClass,
//			String nestedNode, String nestedId) throws Exception {
//		List<Criteria> criteria = new ArrayList<Criteria>();
//		for (Map.Entry<String, String> entry : idMap.entrySet()) {
//			criteria.add(Criteria.where(entry.getKey()).is(entry.getValue()));
//		}
//		AggregationOperation match = Aggregation
//				.match(new Criteria().andOperator(criteria.toArray(new Criteria[criteria.size()])));
//		AggregationOperation unwind = Aggregation.unwind(nestedNode);
//		AggregationOperation replaceRoot = Aggregation.replaceRoot(nestedNode);
//		List<AggregationOperation> allOperations = new ArrayList<>();
//		allOperations.add(match);
//		allOperations.add(unwind);
//		allOperations.add(replaceRoot);
//		Aggregation aggregation = Aggregation.newAggregation(allOperations);
//		List<T> list = (List<T>) mongoTemplate
//				.aggregate(aggregation, mongoTemplate.getCollectionName(docClass), nestedDocClass).getMappedResults();
//		Map<String, Object> map = list.stream().collect(Collectors.toMap(T::getUuid, t -> t));
//		int index = 0;
//		if (idMap.containsKey("index")) {
//			try {
//				index = Integer.parseInt(idMap.get("index"));
//			} catch (Exception ex) {
//				
//			}
//		}
//		T convertobj = null;
//		if (nestedId != null) {
//			convertobj = (T) map.get(nestedId);
//		} else {
//			convertobj = (T) list.get(index);
//		}
//		return convertobj;
//	}
//
//	public <T> List<T> findDistinctDocument(List<QueryParam> require, String groups, Class<?> inputClass,
//			Class<?> outputClass) {
//		List<Criteria> andCriteria = buildCriteria(require);
//		Criteria matchCriteria = new Criteria();
//		if (!andCriteria.isEmpty()) {
//			matchCriteria.andOperator(andCriteria.toArray(new Criteria[0]));
//		}
//
//		AggregationOperation match = Aggregation.match(matchCriteria);
//		String[] groupValues = groups.split("\\s*,\\s*");
//		AggregationOperation group = Aggregation.group(groupValues);
//		AggregationOperation project = Aggregation.project(groupValues);
//
//		List<AggregationOperation> allOperations = new ArrayList<>();
//		allOperations.add(match);
//		allOperations.add(group);
//		allOperations.add(project);
//
//		Aggregation aggregation = Aggregation.newAggregation(allOperations);
//
//		return (List<T>) mongoTemplate.aggregate(aggregation, inputClass, outputClass).getMappedResults();
//	}
//	
//	public <T> List<T> findDistinct(String collectionName, String fieldName, Class<T> targetClass,
//			Map<String, Object> filters) {
//		MongoCollection<Document> collection = mongoDatabase.getCollection(collectionName);
//		//Bson filter = Filters.eq(filterFieldName, filterValue);
//		Bson filter = createFilter(filters);
//		DistinctIterable<T> distinctValues = collection.distinct(fieldName, filter, targetClass);
//		return StreamSupport.stream(distinctValues.spliterator(), false).collect(Collectors.toList());
//	}
//	
//	private Bson createFilter(Map<String, Object> filters) {
//	    List<Bson> filterList = new ArrayList<>();
//	    for (Map.Entry<String, Object> entry : filters.entrySet()) {
//	        filterList.add(Filters.eq(entry.getKey(), entry.getValue()));
//	    }
//	    return Filters.and(filterList);
//	}
//
//	public <T> List<T> findDocument(List<QueryParam> require, Class<?> entityClass) {
//		List<Criteria> andCriteria = buildCriteria(require);
//		Criteria matchCriteria = new Criteria();
//		if (!andCriteria.isEmpty()) {
//			matchCriteria.andOperator(andCriteria.toArray(new Criteria[0]));
//		}
//
//		Query query = new Query(matchCriteria);
//		return (List<T>) mongoTemplate.find(query, entityClass);
//	}
//	
	public <T> Page<T> pagingDocuments(Query require, Pageable pageable, Class<?> entityClass) {
//		List<Criteria> andCriteria = buildCriteria(require);
//		Criteria matchCriteria = new Criteria();
//		if (!andCriteria.isEmpty()) {
//			matchCriteria.andOperator(andCriteria.toArray(new Criteria[0]));
//		}

		Query query = new Query();
		query = require;
		query.with(pageable);
		List<T> items = (List<T>) mongoTemplate.find(query, entityClass);
		Long count = mongoTemplate.count(query.skip(0).limit(0), entityClass);
		return new PageImpl<>(items, pageable, count);
	}
//
	public <T> T deleteDocument(String documentId, Class<?> entityClass) {
		Criteria criteria = Criteria.where(MongoConstant.OBJECT_ID_FIELD).is(documentId);
		Query query = new Query(criteria);
		return (T) mongoTemplate.findAndRemove(query, entityClass);
	}
//	
//	public <T> T deleteDocumentsByKey(String key, List<String> documentIds, Class<?> entityClass) {
//		Criteria criteria = Criteria.where(key).in(documentIds.toArray());
//		Query query = new Query(criteria);
//		return (T) mongoTemplate.findAllAndRemove(query, entityClass);
//	}
//
//	public <T> List<T> lookupDocumentFromCollections(Map<String, String> require, String collectionA, String collectionB, Class<?> outputClass) {
//		Criteria criteria = new Criteria();
//		List<Criteria> criterias = new ArrayList<>();
//		require.forEach((k,v) -> criterias.add(Criteria.where(k).regex(v, "i")));
//		criteria.orOperator(criterias);
//		Aggregation aggregation = Aggregation
//				.newAggregation(Aggregation.match(criteria),
//						UnionWithOperation.unionWith(collectionB)
//						.pipeline(Aggregation.match(criteria)));
//		return (List<T>) mongoTemplate.aggregate(
//				aggregation
//				.withOptions(Aggregation.newAggregationOptions()
//						.allowDiskUse(true)
//						.build()), 
//				collectionA, outputClass).getMappedResults();
//	}
//
//	private List<Criteria> buildCriteria(List<QueryParam> filterFields) {
//		List<Criteria> criterias = new ArrayList<>();
//		for (QueryParam pr : filterFields) {
//			Criteria criteria = pr.init();
//			if (criteria != null) {
//				criterias.add(criteria);
//			}
//		}
//		return criterias;
//	}
//
//	private void buildCriteria(Query query, List<QueryParam> filterFields) {
//		for (QueryParam pr : filterFields) {
//			Criteria criteria = pr.init();
//			if (criteria != null) {
//				query.addCriteria(criteria);
//			}
//		}
//	}
//	
//	public <T> void patchNestedDocument(Class<?> docClass, Map<String, String> idMap, T nestedObject, String nestedNode,
//			Class<?> nestedClass, T model, String key) throws Exception {
//		Update u = new Update().filterArray(Criteria.where("c.uuid").is(idMap.get(key)));
//		ObjectMapper obj = new ObjectMapper();
//		List<Criteria> criteria = new ArrayList<Criteria>();
//		for (Map.Entry<String, String> entry : idMap.entrySet()) {
//			criteria.add(Criteria.where(entry.getKey()).is(entry.getValue()));
//		}
//		Query query = new Query();
//		query.addCriteria(new Criteria().andOperator(criteria.toArray(new Criteria[criteria.size()])));
//		Map<String, Object> props = obj.convertValue(nestedObject, new TypeReference<Map<String, Object>>() {
//		});
//		for (Map.Entry<String, Object> entry : props.entrySet()) {
//			if (entry.getValue() != null) {
//				if (u != null) {
//					u.set(nestedNode + entry.getKey(), entry.getValue());
//				}
//			}
//		}
//
//		mongoTemplate.updateMulti(query, u, docClass);
//	}
//	
//	public UpdateResult deleteNestedObjectById(Class<?> docClass, Map<String, String> idMap, String nestedId,
//			String nestedNode) {
//		List<Criteria> criteria = new ArrayList<Criteria>();
//		for (Map.Entry<String, String> entry : idMap.entrySet()) {
//			criteria.add(Criteria.where(entry.getKey()).is(entry.getValue()));
//		}
//		Query query = new Query();
//		query.addCriteria(new Criteria().andOperator(criteria.toArray(new Criteria[criteria.size()])));
//		Update update = new Update();
//		update.pull(nestedNode, new Query(Criteria.where("uuid").is(nestedId)));
//		return mongoTemplate.updateMulti(query, update, docClass);
//	}
//	
//	public <T> Page<T> findAllNestedDocumentsPages(Class<?> docClass, Map<String, String> idMap,
//			Class<?> nestedDocClass, String nestedNode, Pageable pageable) throws MongoException, Exception {
//		Long limit = (long) (pageable.getPageNumber() * pageable.getPageSize() + pageable.getPageSize());
//		Long skip = (long) (pageable.getPageNumber() * pageable.getPageSize());
//		List<T> items = findAllNestedDocumentWithpagination(docClass, idMap, nestedDocClass, nestedNode, limit, skip);
//		Long count = countAllNestedDocumentWithpagination(docClass, idMap, nestedDocClass, nestedNode);
//		Page<T> page = new PageImpl<>(items, pageable, count);
//		return page;
//	}
//	
//	public <T> List<T> findAllNestedDocumentWithpagination(Class<?> docClass, Map<String, String> idMap,
//			Class<?> nestedDocClass, String nestedNode, Long limitRec, Long skipRec) throws MongoException, Exception {
//		List<Criteria> criteria = new ArrayList<Criteria>();
//		for (Map.Entry<String, String> entry : idMap.entrySet()) {
//			criteria.add(Criteria.where(entry.getKey()).is(entry.getValue()));
//		}
//		AggregationOperation match = Aggregation
//				.match(new Criteria().andOperator(criteria.toArray(new Criteria[criteria.size()])));
//		AggregationOperation unwind = Aggregation.unwind(nestedNode);
//		AggregationOperation replaceRoot = Aggregation.replaceRoot(nestedNode);
//		AggregationOperation limit = Aggregation.limit(limitRec);
//		AggregationOperation skip = Aggregation.skip(skipRec);
//		Aggregation itemsAggregation = Aggregation.newAggregation(match, unwind, replaceRoot, limit, skip);
//
//		List<T> items = (List<T>) mongoTemplate
//				.aggregate(itemsAggregation, mongoTemplate.getCollectionName(docClass), nestedDocClass)
//				.getMappedResults();
//		return items;
//	}
//	
//	public <T> Long countAllNestedDocumentWithpagination(Class<?> docClass, Map<String, String> idMap,
//			Class<?> nestedDocClass, String nestedNode) throws MongoException, Exception {
//		List<Criteria> criteria = new ArrayList<Criteria>();
//		for (Map.Entry<String, String> entry : idMap.entrySet()) {
//			criteria.add(Criteria.where(entry.getKey()).is(entry.getValue()));
//		}
//		AggregationOperation match = Aggregation
//				.match(new Criteria().andOperator(criteria.toArray(new Criteria[criteria.size()])));
//		AggregationOperation unwind = Aggregation.unwind(nestedNode);
//		AggregationOperation replaceRoot = Aggregation.replaceRoot(nestedNode);
//		Aggregation countAggregation = Aggregation.newAggregation(match, unwind, replaceRoot);
//		List<T> countList = (List<T>) mongoTemplate
//				.aggregate(countAggregation, mongoTemplate.getCollectionName(docClass), nestedDocClass)
//				.getMappedResults();
//		Long count = 0L;
//		if (!CollectionUtils.isEmpty(countList)) {
//			count = (long) countList.size();
//		}
//		return count;
//	}
}
