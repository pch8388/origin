package second.sample.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import second.common.dao.AbstractDAO;

@Repository("sampleDAO")
public class SampleDAO extends AbstractDAO{

	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> selectBoardList(Map<String, Object> map) throws Exception{
		return (List<Map<String,Object>>)selectList("sample.selectBoardList",map);
	}

	public void insertBoard(Map<String, Object> map) throws Exception{
		insert("sample.insertBoard",map);
	}

	public void updateHitCnt(Map<String, Object> map) {
		update("sample.updateHitCnt",map);
	}

	@SuppressWarnings("unchecked")
	public Map<String, Object> selectBoardDetail(Map<String, Object> map) {
		return (Map<String,Object>) selectOne("sample.selectBoardDetail",map);
	}

}
