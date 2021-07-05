package web.service;

import java.util.ArrayList;

import web.dao.BoardDAO;
import web.util.ShopException;
import web.vo.ArticleVO;

public class BoardService {
	
	BoardDAO dao;

	public BoardService() {
		dao =new BoardDAO();
	}

	public ArrayList<ArticleVO> listArticles() throws ShopException {
	
		
		return dao.selectAllArticles(); //dao. selectAllArticles 메소드에서 db에 다녀와야함
	}

	public void addArticle(ArticleVO vo) throws ShopException {
		dao.insertNewArticle(vo);
		
	}

	public ArticleVO viewArticle(int articleNO) throws ShopException {
		return dao.selectArticle(articleNO);
	}
	
}
