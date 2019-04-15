package net.sf.eclipsecs.sample.checks;

import com.puppycrawl.tools.checkstyle.api.AbstractCheck;
import com.puppycrawl.tools.checkstyle.api.DetailAST;
import com.puppycrawl.tools.checkstyle.api.TokenTypes;

public class ShotgunSurgery extends AbstractCheck{

	  private int max = 2;	  
	  
	  @Override
	  public int[] getAcceptableTokens() {
	    return new int[] { TokenTypes.CLASS_DEF, TokenTypes.INTERFACE_DEF };
	  }

	  @Override
	  public int[] getRequiredTokens() {
	    return new int[0];
	  }

	  @Override
	  public int[] getDefaultTokens() {
	    return new int[] { TokenTypes.CLASS_DEF, TokenTypes.INTERFACE_DEF };
	  }

	  public void setMax(int limit) {
	    max = limit;
	  }

	  @Override
	  public void visitToken(DetailAST ast) {
	    DetailAST objBlock = ast.findFirstToken(TokenTypes.OBJBLOCK);
	    int count = objBlock.getChildCount(TokenTypes.METHOD_DEF);
	    // report error if it is anti-pattern
	    if (count > max) {
	      log(ast.getLineNo(), "The Anti-pattern is Shotgun surgery", max);
	    }
	  }
}
