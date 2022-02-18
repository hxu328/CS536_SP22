import java_cup.runtime.*; // defines the Symbol class
// The generated scanner will return a Symbol for each token that it finds.
// A Symbol contains an Object field named value; that field will be of type
// TokenVal, defined below.
//
// A TokenVal object contains the line number on which the token occurs as
// well as the number of the character on that line that starts the token.
// Some tokens (literals and IDs) also include the value of the token.
class TokenVal {
  // fields
    int lineNum;
    int charNum;
  // constructor
    TokenVal(int lineNum, int charNum) {
        this.lineNum = lineNum;
        this.charNum = charNum;
    }
}
class IntLitTokenVal extends TokenVal {
  // new field: the value of the integer literal
    int intVal;
  // constructor
    IntLitTokenVal(int lineNum, int charNum, int intVal) {
        super(lineNum, charNum);
        this.intVal = intVal;
    }
}
class IdTokenVal extends TokenVal {
  // new field: the value of the identifier
    String idVal;
  // constructor
    IdTokenVal(int lineNum, int charNum, String idVal) {
        super(lineNum, charNum);
        this.idVal = idVal;
    }
}
class StrLitTokenVal extends TokenVal {
  // new field: the value of the string literal
    String strVal;
  // constructor
    StrLitTokenVal(int lineNum, int charNum, String strVal) {
        super(lineNum, charNum);
        this.strVal = strVal;
    }
}
// The following class is used to keep track of the character number at 
// which the current token starts on its line.
class CharNum {
    static int num = 1;
}


class Yylex implements java_cup.runtime.Scanner {
	private final int YY_BUFFER_SIZE = 512;
	private final int YY_F = -1;
	private final int YY_NO_STATE = -1;
	private final int YY_NOT_ACCEPT = 0;
	private final int YY_START = 1;
	private final int YY_END = 2;
	private final int YY_NO_ANCHOR = 4;
	private final int YY_BOL = 128;
	private final int YY_EOF = 129;

StringBuffer string = new StringBuffer();
	private java.io.BufferedReader yy_reader;
	private int yy_buffer_index;
	private int yy_buffer_read;
	private int yy_buffer_start;
	private int yy_buffer_end;
	private char yy_buffer[];
	private int yyline;
	private boolean yy_at_bol;
	private int yy_lexical_state;

	Yylex (java.io.Reader reader) {
		this ();
		if (null == reader) {
			throw (new Error("Error: Bad input stream initializer."));
		}
		yy_reader = new java.io.BufferedReader(reader);
	}

	Yylex (java.io.InputStream instream) {
		this ();
		if (null == instream) {
			throw (new Error("Error: Bad input stream initializer."));
		}
		yy_reader = new java.io.BufferedReader(new java.io.InputStreamReader(instream));
	}

	private Yylex () {
		yy_buffer = new char[YY_BUFFER_SIZE];
		yy_buffer_read = 0;
		yy_buffer_index = 0;
		yy_buffer_start = 0;
		yy_buffer_end = 0;
		yyline = 0;
		yy_at_bol = true;
		yy_lexical_state = YYINITIAL;
	}

	private boolean yy_eof_done = false;
	private final int STRING = 1;
	private final int YYINITIAL = 0;
	private final int COMMENT = 2;
	private final int yy_state_dtrans[] = {
		0,
		46,
		50
	};
	private void yybegin (int state) {
		yy_lexical_state = state;
	}
	private int yy_advance ()
		throws java.io.IOException {
		int next_read;
		int i;
		int j;

		if (yy_buffer_index < yy_buffer_read) {
			return yy_buffer[yy_buffer_index++];
		}

		if (0 != yy_buffer_start) {
			i = yy_buffer_start;
			j = 0;
			while (i < yy_buffer_read) {
				yy_buffer[j] = yy_buffer[i];
				++i;
				++j;
			}
			yy_buffer_end = yy_buffer_end - yy_buffer_start;
			yy_buffer_start = 0;
			yy_buffer_read = j;
			yy_buffer_index = j;
			next_read = yy_reader.read(yy_buffer,
					yy_buffer_read,
					yy_buffer.length - yy_buffer_read);
			if (-1 == next_read) {
				return YY_EOF;
			}
			yy_buffer_read = yy_buffer_read + next_read;
		}

		while (yy_buffer_index >= yy_buffer_read) {
			if (yy_buffer_index >= yy_buffer.length) {
				yy_buffer = yy_double(yy_buffer);
			}
			next_read = yy_reader.read(yy_buffer,
					yy_buffer_read,
					yy_buffer.length - yy_buffer_read);
			if (-1 == next_read) {
				return YY_EOF;
			}
			yy_buffer_read = yy_buffer_read + next_read;
		}
		return yy_buffer[yy_buffer_index++];
	}
	private void yy_move_end () {
		if (yy_buffer_end > yy_buffer_start &&
		    '\n' == yy_buffer[yy_buffer_end-1])
			yy_buffer_end--;
		if (yy_buffer_end > yy_buffer_start &&
		    '\r' == yy_buffer[yy_buffer_end-1])
			yy_buffer_end--;
	}
	private boolean yy_last_was_cr=false;
	private void yy_mark_start () {
		int i;
		for (i = yy_buffer_start; i < yy_buffer_index; ++i) {
			if ('\n' == yy_buffer[i] && !yy_last_was_cr) {
				++yyline;
			}
			if ('\r' == yy_buffer[i]) {
				++yyline;
				yy_last_was_cr=true;
			} else yy_last_was_cr=false;
		}
		yy_buffer_start = yy_buffer_index;
	}
	private void yy_mark_end () {
		yy_buffer_end = yy_buffer_index;
	}
	private void yy_to_mark () {
		yy_buffer_index = yy_buffer_end;
		yy_at_bol = (yy_buffer_end > yy_buffer_start) &&
		            ('\r' == yy_buffer[yy_buffer_end-1] ||
		             '\n' == yy_buffer[yy_buffer_end-1] ||
		             2028/*LS*/ == yy_buffer[yy_buffer_end-1] ||
		             2029/*PS*/ == yy_buffer[yy_buffer_end-1]);
	}
	private java.lang.String yytext () {
		return (new java.lang.String(yy_buffer,
			yy_buffer_start,
			yy_buffer_end - yy_buffer_start));
	}
	private int yylength () {
		return yy_buffer_end - yy_buffer_start;
	}
	private char[] yy_double (char buf[]) {
		int i;
		char newbuf[];
		newbuf = new char[2*buf.length];
		for (i = 0; i < buf.length; ++i) {
			newbuf[i] = buf[i];
		}
		return newbuf;
	}
	private final int YY_E_INTERNAL = 0;
	private final int YY_E_MATCH = 1;
	private java.lang.String yy_error_string[] = {
		"Error: Internal error.\n",
		"Error: Unmatched input.\n"
	};
	private void yy_error (int code,boolean fatal) {
		java.lang.System.out.print(yy_error_string[code]);
		java.lang.System.out.flush();
		if (fatal) {
			throw new Error("Fatal Error.\n");
		}
	}
	private int[][] unpackFromString(int size1, int size2, String st) {
		int colonIndex = -1;
		String lengthString;
		int sequenceLength = 0;
		int sequenceInteger = 0;

		int commaIndex;
		String workString;

		int res[][] = new int[size1][size2];
		for (int i= 0; i < size1; i++) {
			for (int j= 0; j < size2; j++) {
				if (sequenceLength != 0) {
					res[i][j] = sequenceInteger;
					sequenceLength--;
					continue;
				}
				commaIndex = st.indexOf(',');
				workString = (commaIndex==-1) ? st :
					st.substring(0, commaIndex);
				st = st.substring(commaIndex+1);
				colonIndex = workString.indexOf(':');
				if (colonIndex == -1) {
					res[i][j]=Integer.parseInt(workString);
					continue;
				}
				lengthString =
					workString.substring(colonIndex+1);
				sequenceLength=Integer.parseInt(lengthString);
				workString=workString.substring(0,colonIndex);
				sequenceInteger=Integer.parseInt(workString);
				res[i][j] = sequenceInteger;
				sequenceLength--;
			}
		}
		return res;
	}
	private int yy_acpt[] = {
		/* 0 */ YY_NOT_ACCEPT,
		/* 1 */ YY_NO_ANCHOR,
		/* 2 */ YY_NO_ANCHOR,
		/* 3 */ YY_NO_ANCHOR,
		/* 4 */ YY_NO_ANCHOR,
		/* 5 */ YY_NO_ANCHOR,
		/* 6 */ YY_NO_ANCHOR,
		/* 7 */ YY_NO_ANCHOR,
		/* 8 */ YY_NO_ANCHOR,
		/* 9 */ YY_NO_ANCHOR,
		/* 10 */ YY_NO_ANCHOR,
		/* 11 */ YY_NO_ANCHOR,
		/* 12 */ YY_NO_ANCHOR,
		/* 13 */ YY_NO_ANCHOR,
		/* 14 */ YY_NO_ANCHOR,
		/* 15 */ YY_NO_ANCHOR,
		/* 16 */ YY_NO_ANCHOR,
		/* 17 */ YY_NO_ANCHOR,
		/* 18 */ YY_NO_ANCHOR,
		/* 19 */ YY_NO_ANCHOR,
		/* 20 */ YY_NO_ANCHOR,
		/* 21 */ YY_NO_ANCHOR,
		/* 22 */ YY_NO_ANCHOR,
		/* 23 */ YY_NO_ANCHOR,
		/* 24 */ YY_NO_ANCHOR,
		/* 25 */ YY_NO_ANCHOR,
		/* 26 */ YY_NO_ANCHOR,
		/* 27 */ YY_NO_ANCHOR,
		/* 28 */ YY_NO_ANCHOR,
		/* 29 */ YY_NO_ANCHOR,
		/* 30 */ YY_NO_ANCHOR,
		/* 31 */ YY_NO_ANCHOR,
		/* 32 */ YY_NO_ANCHOR,
		/* 33 */ YY_NO_ANCHOR,
		/* 34 */ YY_NO_ANCHOR,
		/* 35 */ YY_NO_ANCHOR,
		/* 36 */ YY_NO_ANCHOR,
		/* 37 */ YY_NO_ANCHOR,
		/* 38 */ YY_NO_ANCHOR,
		/* 39 */ YY_NO_ANCHOR,
		/* 40 */ YY_NO_ANCHOR,
		/* 41 */ YY_NO_ANCHOR,
		/* 42 */ YY_NO_ANCHOR,
		/* 43 */ YY_NO_ANCHOR,
		/* 44 */ YY_NO_ANCHOR,
		/* 45 */ YY_NO_ANCHOR,
		/* 46 */ YY_NO_ANCHOR,
		/* 47 */ YY_NO_ANCHOR,
		/* 48 */ YY_NO_ANCHOR,
		/* 49 */ YY_NO_ANCHOR,
		/* 50 */ YY_NO_ANCHOR,
		/* 51 */ YY_NO_ANCHOR,
		/* 52 */ YY_NOT_ACCEPT,
		/* 53 */ YY_NO_ANCHOR,
		/* 54 */ YY_NO_ANCHOR,
		/* 55 */ YY_NO_ANCHOR,
		/* 56 */ YY_NO_ANCHOR,
		/* 57 */ YY_NO_ANCHOR,
		/* 58 */ YY_NO_ANCHOR,
		/* 59 */ YY_NOT_ACCEPT,
		/* 60 */ YY_NO_ANCHOR,
		/* 61 */ YY_NO_ANCHOR,
		/* 62 */ YY_NO_ANCHOR,
		/* 63 */ YY_NOT_ACCEPT,
		/* 64 */ YY_NO_ANCHOR,
		/* 65 */ YY_NOT_ACCEPT,
		/* 66 */ YY_NO_ANCHOR,
		/* 67 */ YY_NOT_ACCEPT,
		/* 68 */ YY_NO_ANCHOR,
		/* 69 */ YY_NO_ANCHOR,
		/* 70 */ YY_NO_ANCHOR,
		/* 71 */ YY_NO_ANCHOR,
		/* 72 */ YY_NO_ANCHOR,
		/* 73 */ YY_NO_ANCHOR,
		/* 74 */ YY_NO_ANCHOR,
		/* 75 */ YY_NO_ANCHOR,
		/* 76 */ YY_NO_ANCHOR,
		/* 77 */ YY_NO_ANCHOR,
		/* 78 */ YY_NO_ANCHOR,
		/* 79 */ YY_NO_ANCHOR,
		/* 80 */ YY_NO_ANCHOR,
		/* 81 */ YY_NO_ANCHOR,
		/* 82 */ YY_NO_ANCHOR,
		/* 83 */ YY_NO_ANCHOR,
		/* 84 */ YY_NO_ANCHOR,
		/* 85 */ YY_NO_ANCHOR,
		/* 86 */ YY_NO_ANCHOR,
		/* 87 */ YY_NO_ANCHOR,
		/* 88 */ YY_NO_ANCHOR,
		/* 89 */ YY_NO_ANCHOR,
		/* 90 */ YY_NO_ANCHOR,
		/* 91 */ YY_NO_ANCHOR,
		/* 92 */ YY_NO_ANCHOR,
		/* 93 */ YY_NO_ANCHOR,
		/* 94 */ YY_NO_ANCHOR,
		/* 95 */ YY_NO_ANCHOR,
		/* 96 */ YY_NO_ANCHOR,
		/* 97 */ YY_NO_ANCHOR,
		/* 98 */ YY_NO_ANCHOR,
		/* 99 */ YY_NO_ANCHOR,
		/* 100 */ YY_NO_ANCHOR
	};
	private int yy_cmap[] = unpackFromString(1,130,
"45:9,11,10,45:2,9,45:18,8,42,2,45:2,7,43,45,34,35,5,12,37,41,38,3,1:10,45,3" +
"6,39,44,40,45:2,31:26,45,4,45:2,31,45,27,23,30,19,20,14,31,29,13,31:2,21,31" +
",15,18,26,31,24,22,16,25,17,28,31:3,32,6,33,45:2,0:2")[0];

	private int yy_rmap[] = unpackFromString(1,101,
"0,1,2,1,3,1:3,4,5,6,1:7,7,8,9,10,11,1:2,12,1:8,12:11,1,13,1:3,14,1,15,16,17" +
",18,19,20,21,22,23,24,25,26,27,28,29,30,31,32,33,34,35,36,37,38,39,40,41,42" +
",43,44,45,46,47,48,49,50,51,52,53,54,55,56,57,58,59,60,12,61,62")[0];

	private int yy_nxt[][] = unpackFromString(63,46,
"1,2,3,4,5,6,54,5:2,-1,7,8,9,10,56,98,86,87,98,88,89,98,55,90,99,98:3,100,98" +
":3,11,12,13,14,15,16,17,18,19,20,21,61,22,5,-1:47,2,-1:47,52,-1:53,8,-1:10," +
"8,-1:35,24,-1:34,98,-1:11,98,25,62,98:16,-1:53,26,-1:4,27,-1:41,28,-1:3,29," +
"-1:42,30,-1:48,31,-1:45,33,-1:2,98,-1:11,98:19,-1:14,1,53,47,57,48,57:5,49," +
"57:35,1,60,58:8,51,58:35,-1:4,59,-1:42,53,-1,57,-1,57:5,-1,57:35,-1:6,23,-1" +
":40,98,-1:9,8,-1,98:3,92,98:5,97,98:9,-1:15,98,-1:11,98:14,91,98:4,-1:15,57" +
",-1,57,-1,57:5,-1,57:35,-1,58:9,-1,58:35,-1:5,63,-1:41,60,58:8,-1,58:35,-1:" +
"43,32,-1:3,98,-1:11,98:3,34,98:9,81,98:5,-1:20,65,-1:40,98,-1:11,98:7,35,98" +
":11,-1:21,67,-1:39,98,-1:11,98:6,36,98:12,-1:22,45,-1:38,98,-1:11,98:13,37," +
"98:5,-1:15,98,-1:11,98:7,38,98:11,-1:15,98,-1:11,98:8,39,98:10,-1:15,98,-1:" +
"11,98:3,40,98:15,-1:15,98,-1:11,98:7,41,98:11,-1:15,98,-1:11,98:7,42,98:11," +
"-1:15,98,-1:11,98:3,43,98:15,-1:15,98,-1:11,98:2,44,98:16,-1:15,98,-1:11,98" +
":12,64,98:6,-1:15,98,-1:11,66,98:18,-1:15,98,-1:11,98:9,68,98:9,-1:15,98,-1" +
":11,98:9,69,98:9,-1:15,98,-1:11,98:5,70,98:13,-1:15,98,-1:11,98:12,71,98:6," +
"-1:15,98,-1:11,98:9,72,98:9,-1:15,98,-1:11,98:8,73,98:10,-1:15,98,-1:11,98:" +
"17,74,98,-1:15,98,-1:11,98:11,75,98:7,-1:15,98,-1:11,98:11,76,98:7,-1:15,98" +
",-1:11,98:5,77,98:13,-1:15,98,-1:11,78,98:18,-1:15,98,-1:11,98:8,79,98:10,-" +
"1:15,98,-1:11,98:5,80,98:13,-1:15,98,-1:11,98:8,82,98:10,-1:15,98,-1:11,98:" +
"11,95,98:7,-1:15,98,-1:11,98:3,96,98:15,-1:15,98,-1:11,83,98:18,-1:15,98,-1" +
":11,98:12,84,98:6,-1:15,98,-1:11,98:12,85,98:6,-1:15,98,-1:9,8,-1,98:9,97,9" +
"8:9,-1:15,98,-1:11,98:7,93,98:11,-1:15,98,-1:11,98:16,94,98:2,-1:14");

	public java_cup.runtime.Symbol next_token ()
		throws java.io.IOException {
		int yy_lookahead;
		int yy_anchor = YY_NO_ANCHOR;
		int yy_state = yy_state_dtrans[yy_lexical_state];
		int yy_next_state = YY_NO_STATE;
		int yy_last_accept_state = YY_NO_STATE;
		boolean yy_initial = true;
		int yy_this_accept;

		yy_mark_start();
		yy_this_accept = yy_acpt[yy_state];
		if (YY_NOT_ACCEPT != yy_this_accept) {
			yy_last_accept_state = yy_state;
			yy_mark_end();
		}
		while (true) {
			if (yy_initial && yy_at_bol) yy_lookahead = YY_BOL;
			else yy_lookahead = yy_advance();
			yy_next_state = YY_F;
			yy_next_state = yy_nxt[yy_rmap[yy_state]][yy_cmap[yy_lookahead]];
			if (YY_EOF == yy_lookahead && true == yy_initial) {

return new Symbol(sym.EOF);
			}
			if (YY_F != yy_next_state) {
				yy_state = yy_next_state;
				yy_initial = false;
				yy_this_accept = yy_acpt[yy_state];
				if (YY_NOT_ACCEPT != yy_this_accept) {
					yy_last_accept_state = yy_state;
					yy_mark_end();
				}
			}
			else {
				if (YY_NO_STATE == yy_last_accept_state) {
					throw (new Error("Lexical Error: Unmatched Input."));
				}
				else {
					yy_anchor = yy_acpt[yy_last_accept_state];
					if (0 != (YY_END & yy_anchor)) {
						yy_move_end();
					}
					yy_to_mark();
					switch (yy_last_accept_state) {
					case 1:
						
					case -2:
						break;
					case 2:
						{ // NOTE: the following computation of the integer value does
            //       NOT check for overflow. This must be changed.
            int val = Integer.parseInt(yytext());
            Symbol S = new Symbol(sym.INTLITERAL,
                             new IntLitTokenVal(yyline+1, CharNum.num, val));
            CharNum.num += yytext().length();
            return S;
          }
					case -3:
						break;
					case 3:
						{ string.setLength(0); 
                  string.append('\"'); 
                  CharNum.num += yytext().length(); 
                  yybegin(STRING);}
					case -4:
						break;
					case 4:
						{ Symbol S = new Symbol(sym.DIVIDE,
                          new TokenVal(yyline+1, CharNum.num));
            CharNum.num += yytext().length();
            return S;
          }
					case -5:
						break;
					case 5:
						{ ErrMsg.fatal(yyline+1, CharNum.num,
                         "ignoring illegal character: " + yytext());
            CharNum.num++;
          }
					case -6:
						break;
					case 6:
						{ Symbol S = new Symbol(sym.TIMES,
                          new TokenVal(yyline+1, CharNum.num));
            CharNum.num += yytext().length();
            return S;
          }
					case -7:
						break;
					case 7:
						{ CharNum.num = 1; }
					case -8:
						break;
					case 8:
						{ CharNum.num += yytext().length(); }
					case -9:
						break;
					case 9:
						{ Symbol S = new Symbol(sym.PLUS, new TokenVal(yyline+1, CharNum.num));
            CharNum.num++;
            return S;
          }
					case -10:
						break;
					case 10:
						{ Symbol S = new Symbol (sym.ID,
                                                new IdTokenVal(yyline+1, CharNum.num, yytext()));
                                                CharNum.num += yytext().length();
                                                return S;
                                            }
					case -11:
						break;
					case 11:
						{ Symbol S = new Symbol(sym.LCURLY,
                          new TokenVal(yyline+1, CharNum.num));
            CharNum.num += yytext().length();
            return S;
          }
					case -12:
						break;
					case 12:
						{ Symbol S = new Symbol(sym.RCURLY,
                          new TokenVal(yyline+1, CharNum.num));
            CharNum.num += yytext().length();
            return S;
          }
					case -13:
						break;
					case 13:
						{ Symbol S = new Symbol(sym.LPAREN,
                          new TokenVal(yyline+1, CharNum.num));
            CharNum.num += yytext().length();
            return S;
          }
					case -14:
						break;
					case 14:
						{ Symbol S = new Symbol(sym.RPAREN,
                          new TokenVal(yyline+1, CharNum.num));
            CharNum.num += yytext().length();
            return S;
          }
					case -15:
						break;
					case 15:
						{ Symbol S = new Symbol(sym.SEMICOLON,
                          new TokenVal(yyline+1, CharNum.num));
            CharNum.num += yytext().length();
            return S;
          }
					case -16:
						break;
					case 16:
						{ Symbol S = new Symbol(sym.COMMA,
                          new TokenVal(yyline+1, CharNum.num));
            CharNum.num += yytext().length();
            return S;
          }
					case -17:
						break;
					case 17:
						{ Symbol S = new Symbol(sym.DOT,
                          new TokenVal(yyline+1, CharNum.num));
            CharNum.num += yytext().length();
            return S;
          }
					case -18:
						break;
					case 18:
						{ Symbol S = new Symbol(sym.LESS,
                          new TokenVal(yyline+1, CharNum.num));
            CharNum.num += yytext().length();
            return S;
          }
					case -19:
						break;
					case 19:
						{ Symbol S = new Symbol(sym.GREATER,
                          new TokenVal(yyline+1, CharNum.num));
            CharNum.num += yytext().length();
            return S;
          }
					case -20:
						break;
					case 20:
						{ Symbol S = new Symbol(sym.MINUS,
                          new TokenVal(yyline+1, CharNum.num));
            CharNum.num += yytext().length();
            return S;
          }
					case -21:
						break;
					case 21:
						{ Symbol S = new Symbol(sym.NOT,
                          new TokenVal(yyline+1, CharNum.num));
            CharNum.num += yytext().length();
            return S;
          }
					case -22:
						break;
					case 22:
						{ Symbol S = new Symbol(sym.ASSIGN,
                          new TokenVal(yyline+1, CharNum.num));
            CharNum.num += yytext().length();
            return S;
          }
					case -23:
						break;
					case 23:
						{ Symbol S = new Symbol(sym.OR,
                          new TokenVal(yyline+1, CharNum.num));
            CharNum.num += yytext().length();
            return S;
          }
					case -24:
						break;
					case 24:
						{ Symbol S = new Symbol(sym.PLUSPLUS,
                          new TokenVal(yyline+1, CharNum.num));
            CharNum.num += yytext().length();
            return S;
          }
					case -25:
						break;
					case 25:
						{ Symbol S = new Symbol(sym.IF,
                          new TokenVal(yyline+1, CharNum.num));
            CharNum.num += yytext().length();
            return S;
          }
					case -26:
						break;
					case 26:
						{ Symbol S = new Symbol(sym.WRITE,
                          new TokenVal(yyline+1, CharNum.num));
            CharNum.num += yytext().length();
            return S;
          }
					case -27:
						break;
					case 27:
						{ Symbol S = new Symbol(sym.LESSEQ,
                          new TokenVal(yyline+1, CharNum.num));
            CharNum.num += yytext().length();
            return S;
          }
					case -28:
						break;
					case 28:
						{ Symbol S = new Symbol(sym.READ,
                          new TokenVal(yyline+1, CharNum.num));
            CharNum.num += yytext().length();
            return S;
          }
					case -29:
						break;
					case 29:
						{ Symbol S = new Symbol(sym.GREATEREQ,
                          new TokenVal(yyline+1, CharNum.num));
            CharNum.num += yytext().length();
            return S;
          }
					case -30:
						break;
					case 30:
						{ Symbol S = new Symbol(sym.MINUSMINUS,
                          new TokenVal(yyline+1, CharNum.num));
            CharNum.num += yytext().length();
            return S;
          }
					case -31:
						break;
					case 31:
						{ Symbol S = new Symbol(sym.NOTEQUALS,
                          new TokenVal(yyline+1, CharNum.num));
            CharNum.num += yytext().length();
            return S;
          }
					case -32:
						break;
					case 32:
						{ Symbol S = new Symbol(sym.AND,
                          new TokenVal(yyline+1, CharNum.num));
            CharNum.num += yytext().length();
            return S;
          }
					case -33:
						break;
					case 33:
						{ Symbol S = new Symbol(sym.EQUALS,
                          new TokenVal(yyline+1, CharNum.num));
            CharNum.num += yytext().length();
            return S;
          }
					case -34:
						break;
					case 34:
						{ Symbol S = new Symbol(sym.INT,
                          new TokenVal(yyline+1, CharNum.num));
            CharNum.num += yytext().length();
            return S;
          }
					case -35:
						break;
					case 35:
						{ Symbol S = new Symbol(sym.TRUE,
                          new TokenVal(yyline+1, CharNum.num));
            CharNum.num += yytext().length();
            return S;
          }
					case -36:
						break;
					case 36:
						{ Symbol S = new Symbol(sym.VOID,
                          new TokenVal(yyline+1, CharNum.num));
            CharNum.num += yytext().length();
            return S;
          }
					case -37:
						break;
					case 37:
						{ Symbol S = new Symbol(sym.DISP,
                          new TokenVal(yyline+1, CharNum.num));
            CharNum.num += yytext().length();
            return S;
          }
					case -38:
						break;
					case 38:
						{ Symbol S = new Symbol(sym.ELSE,
                          new TokenVal(yyline+1, CharNum.num));
            CharNum.num += yytext().length();
            return S;
          }
					case -39:
						break;
					case 39:
						{ Symbol S = new Symbol(sym.BOOL,
                          new TokenVal(yyline+1, CharNum.num));
            CharNum.num += yytext().length();
            return S;
          }
					case -40:
						break;
					case 40:
						{ Symbol S = new Symbol(sym.INPUT,
                          new TokenVal(yyline+1, CharNum.num));
            CharNum.num += yytext().length();
            return S;
          }
					case -41:
						break;
					case 41:
						{ Symbol S = new Symbol(sym.FALSE,
                          new TokenVal(yyline+1, CharNum.num));
            CharNum.num += yytext().length();
            return S;
          }
					case -42:
						break;
					case 42:
						{ Symbol S = new Symbol(sym.WHILE,
                          new TokenVal(yyline+1, CharNum.num));
            CharNum.num += yytext().length();
            return S;
          }
					case -43:
						break;
					case 43:
						{ Symbol S = new Symbol(sym.STRUCT,
                          new TokenVal(yyline+1, CharNum.num));
            CharNum.num += yytext().length();
            return S;
          }
					case -44:
						break;
					case 44:
						{ Symbol S = new Symbol(sym.RETURN,
                          new TokenVal(yyline+1, CharNum.num));
            CharNum.num += yytext().length();
            return S;
          }
					case -45:
						break;
					case 45:
						{CharNum.num += yytext().length();}
					case -46:
						break;
					case 46:
						{ CharNum.num += yytext().length();
                        string.append(yytext());}
					case -47:
						break;
					case 47:
						{ yybegin(YYINITIAL);
                string.append('\"');
                CharNum.num += yytext().length();
                return new Symbol(sym.STRINGLITERAL,
                new StrLitTokenVal(yyline+1, CharNum.num, string.toString()));}
					case -48:
						break;
					case 48:
						{ CharNum.num += yytext().length(); string.append('\\');}
					case -49:
						break;
					case 49:
						{ CharNum.num = 1; 
                ErrMsg.fatal(yyline+1, CharNum.num,
                "ignoring new line inside a string.");}
					case -50:
						break;
					case 50:
						{CharNum.num += yytext().length();}
					case -51:
						break;
					case 51:
						{ yybegin(YYINITIAL);
                  CharNum.num = 1;}
					case -52:
						break;
					case 53:
						{ // NOTE: the following computation of the integer value does
            //       NOT check for overflow. This must be changed.
            int val = Integer.parseInt(yytext());
            Symbol S = new Symbol(sym.INTLITERAL,
                             new IntLitTokenVal(yyline+1, CharNum.num, val));
            CharNum.num += yytext().length();
            return S;
          }
					case -53:
						break;
					case 54:
						{ ErrMsg.fatal(yyline+1, CharNum.num,
                         "ignoring illegal character: " + yytext());
            CharNum.num++;
          }
					case -54:
						break;
					case 55:
						{ CharNum.num += yytext().length(); }
					case -55:
						break;
					case 56:
						{ Symbol S = new Symbol (sym.ID,
                                                new IdTokenVal(yyline+1, CharNum.num, yytext()));
                                                CharNum.num += yytext().length();
                                                return S;
                                            }
					case -56:
						break;
					case 57:
						{ CharNum.num += yytext().length();
                        string.append(yytext());}
					case -57:
						break;
					case 58:
						{CharNum.num += yytext().length();}
					case -58:
						break;
					case 60:
						{ // NOTE: the following computation of the integer value does
            //       NOT check for overflow. This must be changed.
            int val = Integer.parseInt(yytext());
            Symbol S = new Symbol(sym.INTLITERAL,
                             new IntLitTokenVal(yyline+1, CharNum.num, val));
            CharNum.num += yytext().length();
            return S;
          }
					case -59:
						break;
					case 61:
						{ ErrMsg.fatal(yyline+1, CharNum.num,
                         "ignoring illegal character: " + yytext());
            CharNum.num++;
          }
					case -60:
						break;
					case 62:
						{ Symbol S = new Symbol (sym.ID,
                                                new IdTokenVal(yyline+1, CharNum.num, yytext()));
                                                CharNum.num += yytext().length();
                                                return S;
                                            }
					case -61:
						break;
					case 64:
						{ Symbol S = new Symbol (sym.ID,
                                                new IdTokenVal(yyline+1, CharNum.num, yytext()));
                                                CharNum.num += yytext().length();
                                                return S;
                                            }
					case -62:
						break;
					case 66:
						{ Symbol S = new Symbol (sym.ID,
                                                new IdTokenVal(yyline+1, CharNum.num, yytext()));
                                                CharNum.num += yytext().length();
                                                return S;
                                            }
					case -63:
						break;
					case 68:
						{ Symbol S = new Symbol (sym.ID,
                                                new IdTokenVal(yyline+1, CharNum.num, yytext()));
                                                CharNum.num += yytext().length();
                                                return S;
                                            }
					case -64:
						break;
					case 69:
						{ Symbol S = new Symbol (sym.ID,
                                                new IdTokenVal(yyline+1, CharNum.num, yytext()));
                                                CharNum.num += yytext().length();
                                                return S;
                                            }
					case -65:
						break;
					case 70:
						{ Symbol S = new Symbol (sym.ID,
                                                new IdTokenVal(yyline+1, CharNum.num, yytext()));
                                                CharNum.num += yytext().length();
                                                return S;
                                            }
					case -66:
						break;
					case 71:
						{ Symbol S = new Symbol (sym.ID,
                                                new IdTokenVal(yyline+1, CharNum.num, yytext()));
                                                CharNum.num += yytext().length();
                                                return S;
                                            }
					case -67:
						break;
					case 72:
						{ Symbol S = new Symbol (sym.ID,
                                                new IdTokenVal(yyline+1, CharNum.num, yytext()));
                                                CharNum.num += yytext().length();
                                                return S;
                                            }
					case -68:
						break;
					case 73:
						{ Symbol S = new Symbol (sym.ID,
                                                new IdTokenVal(yyline+1, CharNum.num, yytext()));
                                                CharNum.num += yytext().length();
                                                return S;
                                            }
					case -69:
						break;
					case 74:
						{ Symbol S = new Symbol (sym.ID,
                                                new IdTokenVal(yyline+1, CharNum.num, yytext()));
                                                CharNum.num += yytext().length();
                                                return S;
                                            }
					case -70:
						break;
					case 75:
						{ Symbol S = new Symbol (sym.ID,
                                                new IdTokenVal(yyline+1, CharNum.num, yytext()));
                                                CharNum.num += yytext().length();
                                                return S;
                                            }
					case -71:
						break;
					case 76:
						{ Symbol S = new Symbol (sym.ID,
                                                new IdTokenVal(yyline+1, CharNum.num, yytext()));
                                                CharNum.num += yytext().length();
                                                return S;
                                            }
					case -72:
						break;
					case 77:
						{ Symbol S = new Symbol (sym.ID,
                                                new IdTokenVal(yyline+1, CharNum.num, yytext()));
                                                CharNum.num += yytext().length();
                                                return S;
                                            }
					case -73:
						break;
					case 78:
						{ Symbol S = new Symbol (sym.ID,
                                                new IdTokenVal(yyline+1, CharNum.num, yytext()));
                                                CharNum.num += yytext().length();
                                                return S;
                                            }
					case -74:
						break;
					case 79:
						{ Symbol S = new Symbol (sym.ID,
                                                new IdTokenVal(yyline+1, CharNum.num, yytext()));
                                                CharNum.num += yytext().length();
                                                return S;
                                            }
					case -75:
						break;
					case 80:
						{ Symbol S = new Symbol (sym.ID,
                                                new IdTokenVal(yyline+1, CharNum.num, yytext()));
                                                CharNum.num += yytext().length();
                                                return S;
                                            }
					case -76:
						break;
					case 81:
						{ Symbol S = new Symbol (sym.ID,
                                                new IdTokenVal(yyline+1, CharNum.num, yytext()));
                                                CharNum.num += yytext().length();
                                                return S;
                                            }
					case -77:
						break;
					case 82:
						{ Symbol S = new Symbol (sym.ID,
                                                new IdTokenVal(yyline+1, CharNum.num, yytext()));
                                                CharNum.num += yytext().length();
                                                return S;
                                            }
					case -78:
						break;
					case 83:
						{ Symbol S = new Symbol (sym.ID,
                                                new IdTokenVal(yyline+1, CharNum.num, yytext()));
                                                CharNum.num += yytext().length();
                                                return S;
                                            }
					case -79:
						break;
					case 84:
						{ Symbol S = new Symbol (sym.ID,
                                                new IdTokenVal(yyline+1, CharNum.num, yytext()));
                                                CharNum.num += yytext().length();
                                                return S;
                                            }
					case -80:
						break;
					case 85:
						{ Symbol S = new Symbol (sym.ID,
                                                new IdTokenVal(yyline+1, CharNum.num, yytext()));
                                                CharNum.num += yytext().length();
                                                return S;
                                            }
					case -81:
						break;
					case 86:
						{ Symbol S = new Symbol (sym.ID,
                                                new IdTokenVal(yyline+1, CharNum.num, yytext()));
                                                CharNum.num += yytext().length();
                                                return S;
                                            }
					case -82:
						break;
					case 87:
						{ Symbol S = new Symbol (sym.ID,
                                                new IdTokenVal(yyline+1, CharNum.num, yytext()));
                                                CharNum.num += yytext().length();
                                                return S;
                                            }
					case -83:
						break;
					case 88:
						{ Symbol S = new Symbol (sym.ID,
                                                new IdTokenVal(yyline+1, CharNum.num, yytext()));
                                                CharNum.num += yytext().length();
                                                return S;
                                            }
					case -84:
						break;
					case 89:
						{ Symbol S = new Symbol (sym.ID,
                                                new IdTokenVal(yyline+1, CharNum.num, yytext()));
                                                CharNum.num += yytext().length();
                                                return S;
                                            }
					case -85:
						break;
					case 90:
						{ Symbol S = new Symbol (sym.ID,
                                                new IdTokenVal(yyline+1, CharNum.num, yytext()));
                                                CharNum.num += yytext().length();
                                                return S;
                                            }
					case -86:
						break;
					case 91:
						{ Symbol S = new Symbol (sym.ID,
                                                new IdTokenVal(yyline+1, CharNum.num, yytext()));
                                                CharNum.num += yytext().length();
                                                return S;
                                            }
					case -87:
						break;
					case 92:
						{ Symbol S = new Symbol (sym.ID,
                                                new IdTokenVal(yyline+1, CharNum.num, yytext()));
                                                CharNum.num += yytext().length();
                                                return S;
                                            }
					case -88:
						break;
					case 93:
						{ Symbol S = new Symbol (sym.ID,
                                                new IdTokenVal(yyline+1, CharNum.num, yytext()));
                                                CharNum.num += yytext().length();
                                                return S;
                                            }
					case -89:
						break;
					case 94:
						{ Symbol S = new Symbol (sym.ID,
                                                new IdTokenVal(yyline+1, CharNum.num, yytext()));
                                                CharNum.num += yytext().length();
                                                return S;
                                            }
					case -90:
						break;
					case 95:
						{ Symbol S = new Symbol (sym.ID,
                                                new IdTokenVal(yyline+1, CharNum.num, yytext()));
                                                CharNum.num += yytext().length();
                                                return S;
                                            }
					case -91:
						break;
					case 96:
						{ Symbol S = new Symbol (sym.ID,
                                                new IdTokenVal(yyline+1, CharNum.num, yytext()));
                                                CharNum.num += yytext().length();
                                                return S;
                                            }
					case -92:
						break;
					case 97:
						{ CharNum.num += yytext().length(); }
					case -93:
						break;
					case 98:
						{ Symbol S = new Symbol (sym.ID,
                                                new IdTokenVal(yyline+1, CharNum.num, yytext()));
                                                CharNum.num += yytext().length();
                                                return S;
                                            }
					case -94:
						break;
					case 99:
						{ Symbol S = new Symbol (sym.ID,
                                                new IdTokenVal(yyline+1, CharNum.num, yytext()));
                                                CharNum.num += yytext().length();
                                                return S;
                                            }
					case -95:
						break;
					case 100:
						{ Symbol S = new Symbol (sym.ID,
                                                new IdTokenVal(yyline+1, CharNum.num, yytext()));
                                                CharNum.num += yytext().length();
                                                return S;
                                            }
					case -96:
						break;
					default:
						yy_error(YY_E_INTERNAL,false);
					case -1:
					}
					yy_initial = true;
					yy_state = yy_state_dtrans[yy_lexical_state];
					yy_next_state = YY_NO_STATE;
					yy_last_accept_state = YY_NO_STATE;
					yy_mark_start();
					yy_this_accept = yy_acpt[yy_state];
					if (YY_NOT_ACCEPT != yy_this_accept) {
						yy_last_accept_state = yy_state;
						yy_mark_end();
					}
				}
			}
		}
	}
}
