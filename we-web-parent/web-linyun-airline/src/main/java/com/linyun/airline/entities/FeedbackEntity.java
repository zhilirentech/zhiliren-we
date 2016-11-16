package com.linyun.airline.entities;

import org.nutz.dao.entity.annotation.*;
import lombok.Data;
import java.util.Date;

import java.io.Serializable;


@Data
@Table("feedback")
public class FeedbackEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id(auto = true)
	private Integer id;
	
	@Column
    @Comment("联系方式")
	private String contact;
	
	@Column
    @Comment("内容")
	private String content;
	
	@Column
    @Comment("备注")
	private String remark;
	
	@Column
    @Comment("反馈时间")
	private Date time;
	

}