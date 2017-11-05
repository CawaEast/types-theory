// Generated from D:/projects/mathLog/tt/3WAlg/src/parser\CLP.g4 by ANTLR 4.7
package parser;

    import grammar.*;
    import java.util.Vector;
    import java.util.HashMap;
    import java.util.HashSet;

import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class CLPParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.7", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, T__6=7, Name=8, Whitespace=9;
	public static final int
		RULE_start = 0, RULE_let = 1, RULE_expression = 2, RULE_application = 3, 
		RULE_atom = 4;
	public static final String[] ruleNames = {
		"start", "let", "expression", "application", "atom"
	};

	private static final String[] _LITERAL_NAMES = {
		null, "'let'", "'='", "'in'", "'\\'", "'.'", "'('", "')'"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, null, null, null, null, null, null, null, "Name", "Whitespace"
	};
	public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
	static {
		tokenNames = new String[_SYMBOLIC_NAMES.length];
		for (int i = 0; i < tokenNames.length; i++) {
			tokenNames[i] = VOCABULARY.getLiteralName(i);
			if (tokenNames[i] == null) {
				tokenNames[i] = VOCABULARY.getSymbolicName(i);
			}

			if (tokenNames[i] == null) {
				tokenNames[i] = "<INVALID>";
			}
		}
	}

	@Override
	@Deprecated
	public String[] getTokenNames() {
		return tokenNames;
	}

	@Override

	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}

	@Override
	public String getGrammarFileName() { return "CLP.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }


	    int counter = 0;
	    HashMap<String, Integer> map = new HashMap<String, Integer>();
	    public GrammarToken lambda;
	    static void wr(String x) {System.out.print(x);}
	    static void tabs(int amount) {for(int i=0; i<amount; i++) System.out.print(' ');}

	public CLPParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}
	public static class StartContext extends ParserRuleContext {
		public LetContext e;
		public TerminalNode EOF() { return getToken(CLPParser.EOF, 0); }
		public LetContext let() {
			return getRuleContext(LetContext.class,0);
		}
		public StartContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_start; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CLPListener ) ((CLPListener)listener).enterStart(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CLPListener ) ((CLPListener)listener).exitStart(this);
		}
	}

	public final StartContext start() throws RecognitionException {
		StartContext _localctx = new StartContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_start);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(10);
			((StartContext)_localctx).e = let();
			lambda = ((StartContext)_localctx).e.rgt; lambda.num = counter;
			setState(12);
			match(EOF);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class LetContext extends ParserRuleContext {
		public GrammarToken rgt;
		public Token v1;
		public LetContext l1;
		public LetContext l2;
		public ExpressionContext e;
		public TerminalNode Name() { return getToken(CLPParser.Name, 0); }
		public List<LetContext> let() {
			return getRuleContexts(LetContext.class);
		}
		public LetContext let(int i) {
			return getRuleContext(LetContext.class,i);
		}
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public LetContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_let; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CLPListener ) ((CLPListener)listener).enterLet(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CLPListener ) ((CLPListener)listener).exitLet(this);
		}
	}

	public final LetContext let() throws RecognitionException {
		LetContext _localctx = new LetContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_let);
		try {
			setState(25);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__0:
				enterOuterAlt(_localctx, 1);
				{
				setState(14);
				match(T__0);
				setState(15);
				((LetContext)_localctx).v1 = match(Name);
				setState(16);
				match(T__1);
				setState(17);
				((LetContext)_localctx).l1 = let();
				setState(18);
				match(T__2);
				setState(19);
				((LetContext)_localctx).l2 = let();
				((LetContext)_localctx).rgt =  new Let(((LetContext)_localctx).l1.rgt, ((LetContext)_localctx).l2.rgt, new Variable((((LetContext)_localctx).v1!=null?((LetContext)_localctx).v1.getText():null)));
				}
				break;
			case T__3:
			case T__5:
			case Name:
				enterOuterAlt(_localctx, 2);
				{
				setState(22);
				((LetContext)_localctx).e = expression();
				((LetContext)_localctx).rgt =  ((LetContext)_localctx).e.rgt;
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ExpressionContext extends ParserRuleContext {
		public GrammarToken rgt;
		public ApplicationContext a1;
		public Token v1;
		public ExpressionContext e1;
		public Token n;
		public ExpressionContext e2;
		public ApplicationContext a3;
		public ApplicationContext application() {
			return getRuleContext(ApplicationContext.class,0);
		}
		public TerminalNode Name() { return getToken(CLPParser.Name, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public ExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expression; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CLPListener ) ((CLPListener)listener).enterExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CLPListener ) ((CLPListener)listener).exitExpression(this);
		}
	}

	public final ExpressionContext expression() throws RecognitionException {
		ExpressionContext _localctx = new ExpressionContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_expression);
		try {
			setState(43);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,1,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(27);
				((ExpressionContext)_localctx).a1 = application(0);
				setState(28);
				match(T__3);
				setState(29);
				((ExpressionContext)_localctx).v1 = match(Name);
				setState(30);
				match(T__4);
				setState(31);
				((ExpressionContext)_localctx).e1 = expression();
				((ExpressionContext)_localctx).rgt =  new Application(((ExpressionContext)_localctx).a1.rgt, new Lambda(new Variable((((ExpressionContext)_localctx).v1!=null?((ExpressionContext)_localctx).v1.getText():null)), ((ExpressionContext)_localctx).e1.rgt));
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(34);
				match(T__3);
				setState(35);
				((ExpressionContext)_localctx).n = match(Name);
				setState(36);
				match(T__4);
				setState(37);
				((ExpressionContext)_localctx).e2 = expression();
				((ExpressionContext)_localctx).rgt =  new Lambda(new Variable((((ExpressionContext)_localctx).n!=null?((ExpressionContext)_localctx).n.getText():null)), ((ExpressionContext)_localctx).e2.rgt);
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(40);
				((ExpressionContext)_localctx).a3 = application(0);
				((ExpressionContext)_localctx).rgt =  ((ExpressionContext)_localctx).a3.rgt;
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ApplicationContext extends ParserRuleContext {
		public GrammarToken rgt;
		public ApplicationContext ap1;
		public AtomContext a2;
		public AtomContext at1;
		public AtomContext atom() {
			return getRuleContext(AtomContext.class,0);
		}
		public ApplicationContext application() {
			return getRuleContext(ApplicationContext.class,0);
		}
		public ApplicationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_application; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CLPListener ) ((CLPListener)listener).enterApplication(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CLPListener ) ((CLPListener)listener).exitApplication(this);
		}
	}

	public final ApplicationContext application() throws RecognitionException {
		return application(0);
	}

	private ApplicationContext application(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		ApplicationContext _localctx = new ApplicationContext(_ctx, _parentState);
		ApplicationContext _prevctx = _localctx;
		int _startState = 6;
		enterRecursionRule(_localctx, 6, RULE_application, _p);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			{
			setState(46);
			((ApplicationContext)_localctx).a2 = atom();
			((ApplicationContext)_localctx).rgt =  ((ApplicationContext)_localctx).a2.rgt;
			}
			_ctx.stop = _input.LT(-1);
			setState(55);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,2,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					{
					_localctx = new ApplicationContext(_parentctx, _parentState);
					_localctx.ap1 = _prevctx;
					_localctx.ap1 = _prevctx;
					pushNewRecursionContext(_localctx, _startState, RULE_application);
					setState(49);
					if (!(precpred(_ctx, 2))) throw new FailedPredicateException(this, "precpred(_ctx, 2)");
					setState(50);
					((ApplicationContext)_localctx).at1 = atom();
					((ApplicationContext)_localctx).rgt =  new Application(((ApplicationContext)_localctx).ap1.rgt, ((ApplicationContext)_localctx).at1.rgt);
					}
					} 
				}
				setState(57);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,2,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	public static class AtomContext extends ParserRuleContext {
		public GrammarToken rgt;
		public Token n;
		public LetContext b;
		public TerminalNode Name() { return getToken(CLPParser.Name, 0); }
		public LetContext let() {
			return getRuleContext(LetContext.class,0);
		}
		public AtomContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_atom; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CLPListener ) ((CLPListener)listener).enterAtom(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CLPListener ) ((CLPListener)listener).exitAtom(this);
		}
	}

	public final AtomContext atom() throws RecognitionException {
		AtomContext _localctx = new AtomContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_atom);
		try {
			setState(65);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case Name:
				enterOuterAlt(_localctx, 1);
				{
				setState(58);
				((AtomContext)_localctx).n = match(Name);
				((AtomContext)_localctx).rgt =  new Variable((((AtomContext)_localctx).n!=null?((AtomContext)_localctx).n.getText():null));
				}
				break;
			case T__5:
				enterOuterAlt(_localctx, 2);
				{
				setState(60);
				match(T__5);
				setState(61);
				((AtomContext)_localctx).b = let();
				((AtomContext)_localctx).rgt =  ((AtomContext)_localctx).b.rgt;
				setState(63);
				match(T__6);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public boolean sempred(RuleContext _localctx, int ruleIndex, int predIndex) {
		switch (ruleIndex) {
		case 3:
			return application_sempred((ApplicationContext)_localctx, predIndex);
		}
		return true;
	}
	private boolean application_sempred(ApplicationContext _localctx, int predIndex) {
		switch (predIndex) {
		case 0:
			return precpred(_ctx, 2);
		}
		return true;
	}

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3\13F\4\2\t\2\4\3\t"+
		"\3\4\4\t\4\4\5\t\5\4\6\t\6\3\2\3\2\3\2\3\2\3\3\3\3\3\3\3\3\3\3\3\3\3\3"+
		"\3\3\3\3\3\3\3\3\5\3\34\n\3\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3"+
		"\4\3\4\3\4\3\4\3\4\3\4\5\4.\n\4\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\7\58\n"+
		"\5\f\5\16\5;\13\5\3\6\3\6\3\6\3\6\3\6\3\6\3\6\5\6D\n\6\3\6\2\3\b\7\2\4"+
		"\6\b\n\2\2\2E\2\f\3\2\2\2\4\33\3\2\2\2\6-\3\2\2\2\b/\3\2\2\2\nC\3\2\2"+
		"\2\f\r\5\4\3\2\r\16\b\2\1\2\16\17\7\2\2\3\17\3\3\2\2\2\20\21\7\3\2\2\21"+
		"\22\7\n\2\2\22\23\7\4\2\2\23\24\5\4\3\2\24\25\7\5\2\2\25\26\5\4\3\2\26"+
		"\27\b\3\1\2\27\34\3\2\2\2\30\31\5\6\4\2\31\32\b\3\1\2\32\34\3\2\2\2\33"+
		"\20\3\2\2\2\33\30\3\2\2\2\34\5\3\2\2\2\35\36\5\b\5\2\36\37\7\6\2\2\37"+
		" \7\n\2\2 !\7\7\2\2!\"\5\6\4\2\"#\b\4\1\2#.\3\2\2\2$%\7\6\2\2%&\7\n\2"+
		"\2&\'\7\7\2\2\'(\5\6\4\2()\b\4\1\2).\3\2\2\2*+\5\b\5\2+,\b\4\1\2,.\3\2"+
		"\2\2-\35\3\2\2\2-$\3\2\2\2-*\3\2\2\2.\7\3\2\2\2/\60\b\5\1\2\60\61\5\n"+
		"\6\2\61\62\b\5\1\2\629\3\2\2\2\63\64\f\4\2\2\64\65\5\n\6\2\65\66\b\5\1"+
		"\2\668\3\2\2\2\67\63\3\2\2\28;\3\2\2\29\67\3\2\2\29:\3\2\2\2:\t\3\2\2"+
		"\2;9\3\2\2\2<=\7\n\2\2=D\b\6\1\2>?\7\b\2\2?@\5\4\3\2@A\b\6\1\2AB\7\t\2"+
		"\2BD\3\2\2\2C<\3\2\2\2C>\3\2\2\2D\13\3\2\2\2\6\33-9C";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}