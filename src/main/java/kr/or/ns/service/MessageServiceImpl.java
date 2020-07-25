package kr.or.ns.service;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.or.ns.dao.MessageDao;
import kr.or.ns.vo.Criteria_Board;
import kr.or.ns.vo.Message;

@Service
public class MessageServiceImpl implements MessageService {

	@Autowired
	private SqlSession sqlsession;

	public int getmsgcount(String user_id) {
		int result = 0;
		MessageDao dao = sqlsession.getMapper(MessageDao.class);
		result = dao.getmsgcount(user_id);
		return result;
	}

	@Override
	@Transactional
	public int insertMessage(Message message) {
		MessageDao dao = sqlsession.getMapper(MessageDao.class);
		int result = 0;
		
		try {
			dao.insertReceptionMessage(message);
			result = dao.insertSendMessage(message);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}

	// 받은편지 목록 뿌리기
	@Override
	public List<Message> getListMessage(String userid) {
		MessageDao dao = sqlsession.getMapper(MessageDao.class);
		List<Message> list = null;
		try {
			System.out.println("아이디 : " + userid);
			list = dao.getListMessage(userid);
			System.out.println("으철 : " + list);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	
	
	//내 메세지 총갯수
	@Override
	public int getMyMessageCount(String user_id) {
		MessageDao dao = sqlsession.getMapper(MessageDao.class);
		int count = dao.getMyMessageCount(user_id);
		
		return count;
	}

	
	
	//받은목록+페이징
	@Override
	public List<HashMap<String, Object>> getMessageList(HashMap<String, Object> map) {
		MessageDao dao = sqlsession.getMapper(MessageDao.class);
		
		Criteria_Board cri_b = (Criteria_Board) map.get("cri_b");
		
		System.out.println("page:"+cri_b.getPage());
		System.out.println("PageStart:"+cri_b.getPageStart());
		System.out.println("PerPageNum:"+cri_b.getPerPageNum());
		
		map.put("pageStart", cri_b.getPageStart());
		map.put("perPageNum", cri_b.getPerPageNum());
		
		List<HashMap<String, Object>> list = dao.getMessageList(map);
		
		return list;
	}
	
	
	
	//보낸목록+페이징
		@Override
		public List<HashMap<String, Object>> getSendMessageList(HashMap<String, Object> map) {
			MessageDao dao = sqlsession.getMapper(MessageDao.class);
			
			Criteria_Board cri_b = (Criteria_Board) map.get("cri_b");
			
			System.out.println("page:"+cri_b.getPage());
			System.out.println("PageStart:"+cri_b.getPageStart());
			System.out.println("PerPageNum:"+cri_b.getPerPageNum());
			
			map.put("pageStart", cri_b.getPageStart());
			map.put("perPageNum", cri_b.getPerPageNum());
			
			List<HashMap<String, Object>> list = dao.getSendMessageList(map);
			
			return list;
		}
	
	
	
	
	
	
	
	
	
	
	
	
	

	// 편지 상세보기
	@Transactional
	@Override
	public HashMap<String, Object> getMessage(String m_seq) {

		MessageDao dao = sqlsession.getMapper(MessageDao.class);
		HashMap<String, Object> message = null;
		try {
			
			System.out.println("2 서비스 왓어욤:"+message);
			System.out.println("아이디 : " + m_seq);
			message = dao.getMessage(m_seq);
			dao.updateMessage(m_seq);
			
			
			System.out.println("3.매퍼갓다왓어욤: "+message);
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return message;
	}

	// 보낸편지 목록 뿌리기
	@Override
	public List<Message> sendListMessage(String userid) {
		MessageDao dao = sqlsession.getMapper(MessageDao.class);
		List<Message> list = null;
		try {
			System.out.println("아이디 : " + userid);
			list = dao.sendListMessage(userid);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	//쪽지 상세페이지(보낸편지함)서 쪽지 하나 삭제
	@Override
	public int deleteSendMessageOne(String m_seq) {
		MessageDao dao = sqlsession.getMapper(MessageDao.class);
		int result = 0;
		try {
			result = dao.deleteSendMessageOne(m_seq);
		} catch (Exception e) {
			e.printStackTrace();
		}

		System.out.println("일단 여긴옴2");
		return result;
	}
	
		//쪽지 상세페이지(받은편지함)서 쪽지 하나 삭제
		@Override
		public int deleteFromMessageOne(String m_seq) {
			MessageDao dao = sqlsession.getMapper(MessageDao.class);
			int result = 0;
			try {
				result = dao.deleteFromMessageOne(m_seq);
			} catch (Exception e) {
				e.printStackTrace();
			}

			System.out.println("일단 여긴옴2");
			return result;
		}

		//받은쪽지 상세
		@Override
		public HashMap<String, Object> getReceptionMessage(String m_seq) {

			MessageDao dao = sqlsession.getMapper(MessageDao.class);
			HashMap<String, Object> message = null;
			try {
				
				System.out.println("2222222 서비스 왓어욤:"+message);
				System.out.println("222222아이디 : " + m_seq);
				message = dao.getReceptionMessage(m_seq);
				dao.updateMessage(m_seq);
				
				
				System.out.println("3.매퍼갓다왓어욤: "+message);
				
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			return message;
		}
	

}
