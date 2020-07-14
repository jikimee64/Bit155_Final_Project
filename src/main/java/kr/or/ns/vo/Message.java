package kr.or.ns.vo;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Message {
	private int m_seq;
	private String receptionid;
	private String senderid;
	private String content;
	private Date opendate;
//	private String opendate;
	private Date senddate;
//	private String senddate;
}
