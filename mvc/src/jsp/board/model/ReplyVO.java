package jsp.board.model;

public class ReplyVO {
		int num;
		String maker;
		String pubdate;
		String content;
		int order;
		int ref;
		int step;
		
		public ReplyVO() {}
		public ReplyVO(int num, String maker, String pubdate, String content,int order) {
			super();
			this.num = num;
			this.maker = maker;
			this.pubdate = pubdate;
			this.content = content;
			this.order = order;
		}
		public ReplyVO(int num, String maker, String pubdate, String content,int order
				,int ref, int step) {
			super();
			this.num = num;
			this.maker = maker;
			this.pubdate = pubdate;
			this.content = content;
			this.order = order;
			this.ref=ref;
			this.step=step;
		}
		public int getNum() {
			return num;
		}
		public void setNum(int num) {
			this.num = num;
		}
		public String getMaker() {
			return maker;
		}
		public void setMaker(String maker) {
			this.maker = maker;
		}
		public String getPubdate() {
			return pubdate;
		}
		public void setPubdate(String pubdate) {
			this.pubdate = pubdate;
		}
		public String getContent() {
			return content;
		}
		public void setContent(String content) {
			this.content = content;
		}
		public int getOrder() {
			return order;
		}
		public void setOrder(int order) {
			this.order = order;
		}
		public int getRef() {
			return ref;
		}
		public void setRef(int ref) {
			this.ref = ref;
		}
		public int getStep() {
			return step;
		}
		public void setStep(int step) {
			this.step = step;
		}
}
