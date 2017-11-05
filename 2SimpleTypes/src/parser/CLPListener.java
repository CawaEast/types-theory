// Generated from D:/projects/mathLog/tt/2SimpleTypes/src/parser\CLP.g4 by ANTLR 4.7
package parser;

    import grammar.*;
    import java.util.Vector;
    import java.util.HashMap;
    import java.util.HashSet;

import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link CLPParser}.
 */
public interface CLPListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link CLPParser#start}.
	 * @param ctx the parse tree
	 */
	void enterStart(CLPParser.StartContext ctx);
	/**
	 * Exit a parse tree produced by {@link CLPParser#start}.
	 * @param ctx the parse tree
	 */
	void exitStart(CLPParser.StartContext ctx);
	/**
	 * Enter a parse tree produced by {@link CLPParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterExpression(CLPParser.ExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link CLPParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitExpression(CLPParser.ExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link CLPParser#application}.
	 * @param ctx the parse tree
	 */
	void enterApplication(CLPParser.ApplicationContext ctx);
	/**
	 * Exit a parse tree produced by {@link CLPParser#application}.
	 * @param ctx the parse tree
	 */
	void exitApplication(CLPParser.ApplicationContext ctx);
	/**
	 * Enter a parse tree produced by {@link CLPParser#atom}.
	 * @param ctx the parse tree
	 */
	void enterAtom(CLPParser.AtomContext ctx);
	/**
	 * Exit a parse tree produced by {@link CLPParser#atom}.
	 * @param ctx the parse tree
	 */
	void exitAtom(CLPParser.AtomContext ctx);
}