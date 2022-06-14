package jsp.board.action;

import jsp.board.model.BoardDAO;

public class Paging {
	private final static int pageBlock = 5;
    private int blockStartNum = 0;
    private int blockLastNum = 0;
    private int lastPageNum = 0;
    private int totalBlock;
    private int pageSize = 15;
    public int getTotalBlock() {
		return totalBlock;
	}

	public void setTotalBlock(int totalBlock) {
		this.totalBlock = totalBlock;
	}
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
    public void makeTotalBlock(int count) {
		if(count%pageSize==0) {
			totalBlock = count/pageSize;
		}else {
			totalBlock = count/pageSize+1;
		}
	}
    // block을 생성
    // 현재 페이지가 속한 block의 시작 번호, 끝 번호를 계산
    public void makeBlock(int curPage){
        if(curPage==1) {
            blockStartNum = 1; 
            blockLastNum = curPage + 2; 
        }else if(curPage == lastPageNum) {
            blockStartNum = curPage-4; 
            blockLastNum = curPage;
    	}else {
        	if(curPage-2 == 0) { //페이징블럭 2를 클릭했을때 ㅇㅇ 
        		blockStartNum = curPage-1; 
            	blockLastNum = curPage + 2;
        	}else if(lastPageNum-curPage==1) {
        		blockStartNum = curPage-3; 
    			blockLastNum = curPage + 1;
        	}else{
        		blockStartNum = curPage-2;
        		blockLastNum = curPage + 2;
        	}
        }
    }

    // 총 페이지의 마지막 번호
    public void makeLastPageNum() {
        BoardDAO dao = BoardDAO.getInstance();
        int total = dao.getArticleCount(); //76개 나올거고

        if( total % pageBlock == 0 ) {
            lastPageNum = (int)Math.floor(total/15);
        }
        else {
            lastPageNum = (int)Math.floor(total/15) + 1;
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
