package org.zxc.spi.impl;

import java.util.List;

import javax.print.Doc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.zxc.spi.Search;

public class FileSearch implements Search {
	
	private static final Logger logger = LoggerFactory.getLogger(FileSearch.class) ;

	@Override
	public List<Doc> search(String keyword) {
		logger.info("now use file system search. keyword:" + keyword) ;
		return null;
	}

}
