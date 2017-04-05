package spms.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.sql.Date;


@Getter
@Setter
@Accessors(chain = true)
public class Project {

	protected int no;
	protected String title;
	protected String content;
	protected Date startDate;
	protected Date endDate;
	protected int state;
	protected Date createdDate;
	protected String tags;

}
