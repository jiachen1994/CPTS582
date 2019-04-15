package net.sf.eclipsecs.sample.checks;

import com.puppycrawl.tools.checkstyle.api.AbstractCheck;
import com.puppycrawl.tools.checkstyle.api.DetailAST;
import com.puppycrawl.tools.checkstyle.api.TokenTypes;

public class DivergentChange extends AbstractCheck {

  @Override
  public int[] getAcceptableTokens() {
    return new int[] { TokenTypes.CTOR_DEF, TokenTypes.METHOD_DEF };
  }

  @Override
  public int[] getRequiredTokens() {
    return new int[0];
  }

  @Override
  public int[] getDefaultTokens() {
    return new int[] { TokenTypes.CTOR_DEF, TokenTypes.METHOD_DEF };
  }

  @Override
  public void visitToken(DetailAST ast) {
    DetailAST objBlock = ast.findFirstToken(TokenTypes.IDENT);
    DetailAST count = objBlock.getParent();
    // report error if it is anti-pattern
    if (count != null) {
      log(objBlock.getLineNo(), "The Anti-pattern is Divergent change");
    }
  }
}
