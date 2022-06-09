package jsp.board.action;

import jsp.board.model.BoardDAO;

public class Paging {
	private final static int pageBlock = 5;
    private int blockStartNum = 0;
    private int blockLastNum = 0;
    private int lastPageNum = 0;
    
    public int getPageBlock() {
    	return pageBlock;
    }
    public int getBlockStartNum() {
        return blockStartNum;
    }
    public void setBlockStartNum(int blockStartNum) {
        this.blockStartNum = blockStartNum;
    }
    public int getBlockLastNum() {
        return blockLastNum;
    }
    public void setBlockLastNum(int blockLastNum) {
        this.blockLastNum = blockLastNum;
    }
    public int getLastPageNum() {
        return lastPageNum;
    }
    public void setLastPageNum(int lastPageNum) {
        this.lastPageNum = lastPageNum;
    }

    // block을 생성
    // 현재 페이지가 속한 block의 시작 번호, 끝 번호를 계산
    public void makeBlock(int curPage){
        int blockNum = 0;

        blockNum = (int)Math.floor((curPage-1)/ pageBlock); // 
        blockStartNum = (pageBlock * blockNum) + 1; //1
        blockLastNum = blockStartNum + (pageBlock-1); 
    }

    // 총 페이지의 마지막 번호
    public void makeLastPageNum() {
        BoardDAO dao = BoardDAO.getInstance();
        int total = dao.getArticleCount();

        if( total % pageBlock == 0 ) {
            lastPageNum = (int)Math.floor(total/pageBlock);
        }
        else {
            lastPageNum = (int)Math.floor(total/pageBlock) + 1;
        }
    }

    // 검색을 했을 때 총 페이지의 마지막 번호
    public void makeLastPageNum(String cate,String kwd) {
    	BoardDAO dao = BoardDAO.getInstance();
        int total = dao.getArticleCountSearch(cate,kwd);

        if( total % pageBlock == 0 ) {
            lastPageNum = (int)Math.floor(total/pageBlock);
        }
        else {
            lastPageNum = (int)Math.floor(total/pageBlock) + 1;
        }
    }
}
