// $ANTLR 3.4 /home/arv/workspace/master/meteor/meteor-meteor/src/main/java/eu/stratosphere/meteor/Meteor.g 2012-11-16 17:20:31
 
package eu.stratosphere.meteor; 

import eu.stratosphere.sopremo.operator.*;
import eu.stratosphere.sopremo.io.*;
import eu.stratosphere.sopremo.query.*;
import eu.stratosphere.sopremo.pact.*;
import eu.stratosphere.sopremo.expressions.*;
import eu.stratosphere.sopremo.function.*;
import java.math.*;
import java.util.IdentityHashMap;


import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

import org.antlr.runtime.tree.*;


@SuppressWarnings({"all", "warnings", "unchecked"})
public class MeteorParser extends MeteorParserBase {
    public static final String[] tokenNames = new String[] {
        "<invalid>", "<EOR>", "<DOWN>", "<UP>", "AND", "APOSTROPHE", "AS", "COMMENT", "DECIMAL", "DIGIT", "ELSE", "ESC_SEQ", "EXPONENT", "EXPRESSION", "FN", "HEX_DIGIT", "ID", "IF", "IN", "INTEGER", "JAVAUDF", "LOWER_LETTER", "NOT", "OCTAL_ESC", "OPERATOR", "OR", "QUOTATION", "SIGN", "STAR", "STRING", "UINT", "UNICODE_ESC", "UPPER_LETTER", "VAR", "WS", "'!'", "'!='", "'&&'", "'&'", "'('", "')'", "'+'", "'++'", "','", "'-'", "'--'", "'.'", "'/'", "':'", "';'", "'<'", "'<='", "'='", "'=='", "'>'", "'>='", "'?'", "'?.'", "'['", "']'", "'false'", "'from'", "'null'", "'read'", "'to'", "'true'", "'using'", "'write'", "'{'", "'||'", "'}'", "'~'"
    };

    public static final int EOF=-1;
    public static final int T__35=35;
    public static final int T__36=36;
    public static final int T__37=37;
    public static final int T__38=38;
    public static final int T__39=39;
    public static final int T__40=40;
    public static final int T__41=41;
    public static final int T__42=42;
    public static final int T__43=43;
    public static final int T__44=44;
    public static final int T__45=45;
    public static final int T__46=46;
    public static final int T__47=47;
    public static final int T__48=48;
    public static final int T__49=49;
    public static final int T__50=50;
    public static final int T__51=51;
    public static final int T__52=52;
    public static final int T__53=53;
    public static final int T__54=54;
    public static final int T__55=55;
    public static final int T__56=56;
    public static final int T__57=57;
    public static final int T__58=58;
    public static final int T__59=59;
    public static final int T__60=60;
    public static final int T__61=61;
    public static final int T__62=62;
    public static final int T__63=63;
    public static final int T__64=64;
    public static final int T__65=65;
    public static final int T__66=66;
    public static final int T__67=67;
    public static final int T__68=68;
    public static final int T__69=69;
    public static final int T__70=70;
    public static final int T__71=71;
    public static final int AND=4;
    public static final int APOSTROPHE=5;
    public static final int AS=6;
    public static final int COMMENT=7;
    public static final int DECIMAL=8;
    public static final int DIGIT=9;
    public static final int ELSE=10;
    public static final int ESC_SEQ=11;
    public static final int EXPONENT=12;
    public static final int EXPRESSION=13;
    public static final int FN=14;
    public static final int HEX_DIGIT=15;
    public static final int ID=16;
    public static final int IF=17;
    public static final int IN=18;
    public static final int INTEGER=19;
    public static final int JAVAUDF=20;
    public static final int LOWER_LETTER=21;
    public static final int NOT=22;
    public static final int OCTAL_ESC=23;
    public static final int OPERATOR=24;
    public static final int OR=25;
    public static final int QUOTATION=26;
    public static final int SIGN=27;
    public static final int STAR=28;
    public static final int STRING=29;
    public static final int UINT=30;
    public static final int UNICODE_ESC=31;
    public static final int UPPER_LETTER=32;
    public static final int VAR=33;
    public static final int WS=34;

    // delegates
    public MeteorParserBase[] getDelegates() {
        return new MeteorParserBase[] {};
    }

    // delegators


    public MeteorParser(TokenStream input) {
        this(input, new RecognizerSharedState());
    }
    public MeteorParser(TokenStream input, RecognizerSharedState state) {
        super(input, state);
    }

protected TreeAdaptor adaptor = new CommonTreeAdaptor();

public void setTreeAdaptor(TreeAdaptor adaptor) {
    this.adaptor = adaptor;
}
public TreeAdaptor getTreeAdaptor() {
    return adaptor;
}
    public String[] getTokenNames() { return MeteorParser.tokenNames; }
    public String getGrammarFileName() { return "/home/arv/workspace/master/meteor/meteor-meteor/src/main/java/eu/stratosphere/meteor/Meteor.g"; }


      private Stack<String> paraphrase = new Stack<String>();

      private boolean setInnerOutput(Token VAR, Operator<?> op) {
    	  JsonStreamExpression output = new JsonStreamExpression(((operator_scope)operator_stack.peek()).result.getOutput(((objectCreation_scope)objectCreation_stack.peek()).mappings.size()));
    	  ((objectCreation_scope)objectCreation_stack.peek()).mappings.add(new ObjectCreation.TagMapping(output, new JsonStreamExpression(op)));
    	  getVariableRegistry().getRegistry(1).put(VAR.getText(), output);
    	  return true;
    	}
      
      protected EvaluationExpression getInputSelection(Token inputVar) {
          return getVariable(inputVar).toInputSelection(((operator_scope)operator_stack.peek()).result);
      }

      public void parseSinks() throws RecognitionException {
        script();
      }


    public static class script_return extends ParserRuleReturnScope {
        EvaluationExpression tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "script"
    // /home/arv/workspace/master/meteor/meteor-meteor/src/main/java/eu/stratosphere/meteor/Meteor.g:59:1: script : ( statement ';' )+ ->;
    public final MeteorParser.script_return script() throws RecognitionException {
        MeteorParser.script_return retval = new MeteorParser.script_return();
        retval.start = input.LT(1);


        EvaluationExpression root_0 = null;

        Token char_literal2=null;
        MeteorParser.statement_return statement1 =null;


        EvaluationExpression char_literal2_tree=null;
        RewriteRuleTokenStream stream_49=new RewriteRuleTokenStream(adaptor,"token 49");
        RewriteRuleSubtreeStream stream_statement=new RewriteRuleSubtreeStream(adaptor,"rule statement");
        try {
            // /home/arv/workspace/master/meteor/meteor-meteor/src/main/java/eu/stratosphere/meteor/Meteor.g:60:2: ( ( statement ';' )+ ->)
            // /home/arv/workspace/master/meteor/meteor-meteor/src/main/java/eu/stratosphere/meteor/Meteor.g:60:5: ( statement ';' )+
            {
            // /home/arv/workspace/master/meteor/meteor-meteor/src/main/java/eu/stratosphere/meteor/Meteor.g:60:5: ( statement ';' )+
            int cnt1=0;
            loop1:
            do {
                int alt1=2;
                int LA1_0 = input.LA(1);

                if ( (LA1_0==ID||LA1_0==VAR||LA1_0==63||(LA1_0 >= 66 && LA1_0 <= 67)) ) {
                    alt1=1;
                }


                switch (alt1) {
            	case 1 :
            	    // /home/arv/workspace/master/meteor/meteor-meteor/src/main/java/eu/stratosphere/meteor/Meteor.g:60:6: statement ';'
            	    {
            	    pushFollow(FOLLOW_statement_in_script121);
            	    statement1=statement();

            	    state._fsp--;
            	    if (state.failed) return retval;
            	    if ( state.backtracking==0 ) stream_statement.add(statement1.getTree());

            	    char_literal2=(Token)match(input,49,FOLLOW_49_in_script123); if (state.failed) return retval; 
            	    if ( state.backtracking==0 ) stream_49.add(char_literal2);


            	    }
            	    break;

            	default :
            	    if ( cnt1 >= 1 ) break loop1;
            	    if (state.backtracking>0) {state.failed=true; return retval;}
                        EarlyExitException eee =
                            new EarlyExitException(1, input);
                        throw eee;
                }
                cnt1++;
            } while (true);


            // AST REWRITE
            // elements: 
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            if ( state.backtracking==0 ) {

            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

            root_0 = (EvaluationExpression)adaptor.nil();
            // 60:22: ->
            {
                root_0 = null;
            }


            retval.tree = root_0;
            }

            }

            retval.stop = input.LT(-1);


            if ( state.backtracking==0 ) {

            retval.tree = (EvaluationExpression)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }

        catch (RecognitionException e) {
          throw e;
        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "script"


    public static class statement_return extends ParserRuleReturnScope {
        EvaluationExpression tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "statement"
    // /home/arv/workspace/master/meteor/meteor-meteor/src/main/java/eu/stratosphere/meteor/Meteor.g:62:1: statement : ( assignment | operator | packageImport | functionDefinition | javaudf ) ->;
    public final MeteorParser.statement_return statement() throws RecognitionException {
        MeteorParser.statement_return retval = new MeteorParser.statement_return();
        retval.start = input.LT(1);


        EvaluationExpression root_0 = null;

        MeteorParser.assignment_return assignment3 =null;

        MeteorParser.operator_return operator4 =null;

        MeteorParser.packageImport_return packageImport5 =null;

        MeteorParser.functionDefinition_return functionDefinition6 =null;

        MeteorParser.javaudf_return javaudf7 =null;


        RewriteRuleSubtreeStream stream_assignment=new RewriteRuleSubtreeStream(adaptor,"rule assignment");
        RewriteRuleSubtreeStream stream_functionDefinition=new RewriteRuleSubtreeStream(adaptor,"rule functionDefinition");
        RewriteRuleSubtreeStream stream_javaudf=new RewriteRuleSubtreeStream(adaptor,"rule javaudf");
        RewriteRuleSubtreeStream stream_operator=new RewriteRuleSubtreeStream(adaptor,"rule operator");
        RewriteRuleSubtreeStream stream_packageImport=new RewriteRuleSubtreeStream(adaptor,"rule packageImport");
        try {
            // /home/arv/workspace/master/meteor/meteor-meteor/src/main/java/eu/stratosphere/meteor/Meteor.g:63:2: ( ( assignment | operator | packageImport | functionDefinition | javaudf ) ->)
            // /home/arv/workspace/master/meteor/meteor-meteor/src/main/java/eu/stratosphere/meteor/Meteor.g:63:4: ( assignment | operator | packageImport | functionDefinition | javaudf )
            {
            // /home/arv/workspace/master/meteor/meteor-meteor/src/main/java/eu/stratosphere/meteor/Meteor.g:63:4: ( assignment | operator | packageImport | functionDefinition | javaudf )
            int alt2=5;
            switch ( input.LA(1) ) {
            case VAR:
                {
                alt2=1;
                }
                break;
            case 63:
            case 67:
                {
                alt2=2;
                }
                break;
            case ID:
                {
                int LA2_3 = input.LA(2);

                if ( (LA2_3==ID||LA2_3==VAR||LA2_3==48||LA2_3==58) ) {
                    alt2=2;
                }
                else if ( (LA2_3==52) ) {
                    int LA2_5 = input.LA(3);

                    if ( (LA2_5==JAVAUDF) ) {
                        alt2=5;
                    }
                    else if ( (LA2_5==FN) ) {
                        alt2=4;
                    }
                    else {
                        if (state.backtracking>0) {state.failed=true; return retval;}
                        NoViableAltException nvae =
                            new NoViableAltException("", 2, 5, input);

                        throw nvae;

                    }
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return retval;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 2, 3, input);

                    throw nvae;

                }
                }
                break;
            case 66:
                {
                alt2=3;
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("", 2, 0, input);

                throw nvae;

            }

            switch (alt2) {
                case 1 :
                    // /home/arv/workspace/master/meteor/meteor-meteor/src/main/java/eu/stratosphere/meteor/Meteor.g:63:5: assignment
                    {
                    pushFollow(FOLLOW_assignment_in_statement137);
                    assignment3=assignment();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_assignment.add(assignment3.getTree());

                    }
                    break;
                case 2 :
                    // /home/arv/workspace/master/meteor/meteor-meteor/src/main/java/eu/stratosphere/meteor/Meteor.g:63:18: operator
                    {
                    pushFollow(FOLLOW_operator_in_statement141);
                    operator4=operator();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_operator.add(operator4.getTree());

                    }
                    break;
                case 3 :
                    // /home/arv/workspace/master/meteor/meteor-meteor/src/main/java/eu/stratosphere/meteor/Meteor.g:63:29: packageImport
                    {
                    pushFollow(FOLLOW_packageImport_in_statement145);
                    packageImport5=packageImport();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_packageImport.add(packageImport5.getTree());

                    }
                    break;
                case 4 :
                    // /home/arv/workspace/master/meteor/meteor-meteor/src/main/java/eu/stratosphere/meteor/Meteor.g:63:45: functionDefinition
                    {
                    pushFollow(FOLLOW_functionDefinition_in_statement149);
                    functionDefinition6=functionDefinition();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_functionDefinition.add(functionDefinition6.getTree());

                    }
                    break;
                case 5 :
                    // /home/arv/workspace/master/meteor/meteor-meteor/src/main/java/eu/stratosphere/meteor/Meteor.g:63:66: javaudf
                    {
                    pushFollow(FOLLOW_javaudf_in_statement153);
                    javaudf7=javaudf();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_javaudf.add(javaudf7.getTree());

                    }
                    break;

            }


            // AST REWRITE
            // elements: 
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            if ( state.backtracking==0 ) {

            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

            root_0 = (EvaluationExpression)adaptor.nil();
            // 63:75: ->
            {
                root_0 = null;
            }


            retval.tree = root_0;
            }

            }

            retval.stop = input.LT(-1);


            if ( state.backtracking==0 ) {

            retval.tree = (EvaluationExpression)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }

        catch (RecognitionException e) {
          throw e;
        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "statement"


    public static class packageImport_return extends ParserRuleReturnScope {
        EvaluationExpression tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "packageImport"
    // /home/arv/workspace/master/meteor/meteor-meteor/src/main/java/eu/stratosphere/meteor/Meteor.g:65:1: packageImport : 'using' packageName= ID ( ',' additionalPackage= ID )* ->;
    public final MeteorParser.packageImport_return packageImport() throws RecognitionException {
        MeteorParser.packageImport_return retval = new MeteorParser.packageImport_return();
        retval.start = input.LT(1);


        EvaluationExpression root_0 = null;

        Token packageName=null;
        Token additionalPackage=null;
        Token string_literal8=null;
        Token char_literal9=null;

        EvaluationExpression packageName_tree=null;
        EvaluationExpression additionalPackage_tree=null;
        EvaluationExpression string_literal8_tree=null;
        EvaluationExpression char_literal9_tree=null;
        RewriteRuleTokenStream stream_66=new RewriteRuleTokenStream(adaptor,"token 66");
        RewriteRuleTokenStream stream_43=new RewriteRuleTokenStream(adaptor,"token 43");
        RewriteRuleTokenStream stream_ID=new RewriteRuleTokenStream(adaptor,"token ID");

        try {
            // /home/arv/workspace/master/meteor/meteor-meteor/src/main/java/eu/stratosphere/meteor/Meteor.g:66:3: ( 'using' packageName= ID ( ',' additionalPackage= ID )* ->)
            // /home/arv/workspace/master/meteor/meteor-meteor/src/main/java/eu/stratosphere/meteor/Meteor.g:66:6: 'using' packageName= ID ( ',' additionalPackage= ID )*
            {
            string_literal8=(Token)match(input,66,FOLLOW_66_in_packageImport168); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_66.add(string_literal8);


            packageName=(Token)match(input,ID,FOLLOW_ID_in_packageImport172); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_ID.add(packageName);


            if ( state.backtracking==0 ) { getPackageManager().importPackage((packageName!=null?packageName.getText():null)); }

            // /home/arv/workspace/master/meteor/meteor-meteor/src/main/java/eu/stratosphere/meteor/Meteor.g:67:6: ( ',' additionalPackage= ID )*
            loop3:
            do {
                int alt3=2;
                int LA3_0 = input.LA(1);

                if ( (LA3_0==43) ) {
                    alt3=1;
                }


                switch (alt3) {
            	case 1 :
            	    // /home/arv/workspace/master/meteor/meteor-meteor/src/main/java/eu/stratosphere/meteor/Meteor.g:67:7: ',' additionalPackage= ID
            	    {
            	    char_literal9=(Token)match(input,43,FOLLOW_43_in_packageImport183); if (state.failed) return retval; 
            	    if ( state.backtracking==0 ) stream_43.add(char_literal9);


            	    additionalPackage=(Token)match(input,ID,FOLLOW_ID_in_packageImport187); if (state.failed) return retval; 
            	    if ( state.backtracking==0 ) stream_ID.add(additionalPackage);


            	    if ( state.backtracking==0 ) { getPackageManager().importPackage((additionalPackage!=null?additionalPackage.getText():null)); }

            	    }
            	    break;

            	default :
            	    break loop3;
                }
            } while (true);


            // AST REWRITE
            // elements: 
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            if ( state.backtracking==0 ) {

            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

            root_0 = (EvaluationExpression)adaptor.nil();
            // 67:98: ->
            {
                root_0 = null;
            }


            retval.tree = root_0;
            }

            }

            retval.stop = input.LT(-1);


            if ( state.backtracking==0 ) {

            retval.tree = (EvaluationExpression)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }

        catch (RecognitionException e) {
          throw e;
        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "packageImport"


    public static class assignment_return extends ParserRuleReturnScope {
        EvaluationExpression tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "assignment"
    // /home/arv/workspace/master/meteor/meteor-meteor/src/main/java/eu/stratosphere/meteor/Meteor.g:69:1: assignment : target= VAR '=' source= operator ->;
    public final MeteorParser.assignment_return assignment() throws RecognitionException {
        MeteorParser.assignment_return retval = new MeteorParser.assignment_return();
        retval.start = input.LT(1);


        EvaluationExpression root_0 = null;

        Token target=null;
        Token char_literal10=null;
        MeteorParser.operator_return source =null;


        EvaluationExpression target_tree=null;
        EvaluationExpression char_literal10_tree=null;
        RewriteRuleTokenStream stream_VAR=new RewriteRuleTokenStream(adaptor,"token VAR");
        RewriteRuleTokenStream stream_52=new RewriteRuleTokenStream(adaptor,"token 52");
        RewriteRuleSubtreeStream stream_operator=new RewriteRuleSubtreeStream(adaptor,"rule operator");
        try {
            // /home/arv/workspace/master/meteor/meteor-meteor/src/main/java/eu/stratosphere/meteor/Meteor.g:70:2: (target= VAR '=' source= operator ->)
            // /home/arv/workspace/master/meteor/meteor-meteor/src/main/java/eu/stratosphere/meteor/Meteor.g:70:4: target= VAR '=' source= operator
            {
            target=(Token)match(input,VAR,FOLLOW_VAR_in_assignment204); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_VAR.add(target);


            char_literal10=(Token)match(input,52,FOLLOW_52_in_assignment206); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_52.add(char_literal10);


            pushFollow(FOLLOW_operator_in_assignment210);
            source=operator();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) stream_operator.add(source.getTree());

            if ( state.backtracking==0 ) { putVariable(target, new JsonStreamExpression((source!=null?source.op:null))); }

            // AST REWRITE
            // elements: 
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            if ( state.backtracking==0 ) {

            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

            root_0 = (EvaluationExpression)adaptor.nil();
            // 70:99: ->
            {
                root_0 = null;
            }


            retval.tree = root_0;
            }

            }

            retval.stop = input.LT(-1);


            if ( state.backtracking==0 ) {

            retval.tree = (EvaluationExpression)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }

        catch (RecognitionException e) {
          throw e;
        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "assignment"


    public static class functionDefinition_return extends ParserRuleReturnScope {
        EvaluationExpression tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "functionDefinition"
    // /home/arv/workspace/master/meteor/meteor-meteor/src/main/java/eu/stratosphere/meteor/Meteor.g:72:1: functionDefinition : name= ID '=' func= inlineFunction ->;
    public final MeteorParser.functionDefinition_return functionDefinition() throws RecognitionException {
        MeteorParser.functionDefinition_return retval = new MeteorParser.functionDefinition_return();
        retval.start = input.LT(1);


        EvaluationExpression root_0 = null;

        Token name=null;
        Token char_literal11=null;
        MeteorParser.inlineFunction_return func =null;


        EvaluationExpression name_tree=null;
        EvaluationExpression char_literal11_tree=null;
        RewriteRuleTokenStream stream_ID=new RewriteRuleTokenStream(adaptor,"token ID");
        RewriteRuleTokenStream stream_52=new RewriteRuleTokenStream(adaptor,"token 52");
        RewriteRuleSubtreeStream stream_inlineFunction=new RewriteRuleSubtreeStream(adaptor,"rule inlineFunction");
        try {
            // /home/arv/workspace/master/meteor/meteor-meteor/src/main/java/eu/stratosphere/meteor/Meteor.g:73:3: (name= ID '=' func= inlineFunction ->)
            // /home/arv/workspace/master/meteor/meteor-meteor/src/main/java/eu/stratosphere/meteor/Meteor.g:73:5: name= ID '=' func= inlineFunction
            {
            name=(Token)match(input,ID,FOLLOW_ID_in_functionDefinition227); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_ID.add(name);


            char_literal11=(Token)match(input,52,FOLLOW_52_in_functionDefinition229); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_52.add(char_literal11);


            pushFollow(FOLLOW_inlineFunction_in_functionDefinition233);
            func=inlineFunction();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) stream_inlineFunction.add(func.getTree());

            if ( state.backtracking==0 ) { addFunction((name!=null?name.getText():null), (func!=null?func.func:null)); }

            // AST REWRITE
            // elements: 
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            if ( state.backtracking==0 ) {

            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

            root_0 = (EvaluationExpression)adaptor.nil();
            // 73:78: ->
            {
                root_0 = null;
            }


            retval.tree = root_0;
            }

            }

            retval.stop = input.LT(-1);


            if ( state.backtracking==0 ) {

            retval.tree = (EvaluationExpression)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }

        catch (RecognitionException e) {
          throw e;
        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "functionDefinition"


    public static class inlineFunction_return extends ParserRuleReturnScope {
        public ExpressionFunction func;
        EvaluationExpression tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "inlineFunction"
    // /home/arv/workspace/master/meteor/meteor-meteor/src/main/java/eu/stratosphere/meteor/Meteor.g:75:1: inlineFunction returns [ExpressionFunction func] : FN '(' (param= ID ( ',' param= ID )* )? ')' def= contextAwareExpression[null] ->;
    public final MeteorParser.inlineFunction_return inlineFunction() throws RecognitionException {
        MeteorParser.inlineFunction_return retval = new MeteorParser.inlineFunction_return();
        retval.start = input.LT(1);


        EvaluationExpression root_0 = null;

        Token param=null;
        Token FN12=null;
        Token char_literal13=null;
        Token char_literal14=null;
        Token char_literal15=null;
        MeteorParser.contextAwareExpression_return def =null;


        EvaluationExpression param_tree=null;
        EvaluationExpression FN12_tree=null;
        EvaluationExpression char_literal13_tree=null;
        EvaluationExpression char_literal14_tree=null;
        EvaluationExpression char_literal15_tree=null;
        RewriteRuleTokenStream stream_43=new RewriteRuleTokenStream(adaptor,"token 43");
        RewriteRuleTokenStream stream_FN=new RewriteRuleTokenStream(adaptor,"token FN");
        RewriteRuleTokenStream stream_40=new RewriteRuleTokenStream(adaptor,"token 40");
        RewriteRuleTokenStream stream_ID=new RewriteRuleTokenStream(adaptor,"token ID");
        RewriteRuleTokenStream stream_39=new RewriteRuleTokenStream(adaptor,"token 39");
        RewriteRuleSubtreeStream stream_contextAwareExpression=new RewriteRuleSubtreeStream(adaptor,"rule contextAwareExpression");
         List<Token> params = new ArrayList(); 
        try {
            // /home/arv/workspace/master/meteor/meteor-meteor/src/main/java/eu/stratosphere/meteor/Meteor.g:77:3: ( FN '(' (param= ID ( ',' param= ID )* )? ')' def= contextAwareExpression[null] ->)
            // /home/arv/workspace/master/meteor/meteor-meteor/src/main/java/eu/stratosphere/meteor/Meteor.g:77:5: FN '(' (param= ID ( ',' param= ID )* )? ')' def= contextAwareExpression[null]
            {
            FN12=(Token)match(input,FN,FOLLOW_FN_in_inlineFunction259); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_FN.add(FN12);


            char_literal13=(Token)match(input,39,FOLLOW_39_in_inlineFunction261); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_39.add(char_literal13);


            // /home/arv/workspace/master/meteor/meteor-meteor/src/main/java/eu/stratosphere/meteor/Meteor.g:78:3: (param= ID ( ',' param= ID )* )?
            int alt5=2;
            int LA5_0 = input.LA(1);

            if ( (LA5_0==ID) ) {
                alt5=1;
            }
            switch (alt5) {
                case 1 :
                    // /home/arv/workspace/master/meteor/meteor-meteor/src/main/java/eu/stratosphere/meteor/Meteor.g:78:4: param= ID ( ',' param= ID )*
                    {
                    param=(Token)match(input,ID,FOLLOW_ID_in_inlineFunction270); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_ID.add(param);


                    if ( state.backtracking==0 ) { params.add(param); }

                    // /home/arv/workspace/master/meteor/meteor-meteor/src/main/java/eu/stratosphere/meteor/Meteor.g:79:3: ( ',' param= ID )*
                    loop4:
                    do {
                        int alt4=2;
                        int LA4_0 = input.LA(1);

                        if ( (LA4_0==43) ) {
                            alt4=1;
                        }


                        switch (alt4) {
                    	case 1 :
                    	    // /home/arv/workspace/master/meteor/meteor-meteor/src/main/java/eu/stratosphere/meteor/Meteor.g:79:4: ',' param= ID
                    	    {
                    	    char_literal14=(Token)match(input,43,FOLLOW_43_in_inlineFunction277); if (state.failed) return retval; 
                    	    if ( state.backtracking==0 ) stream_43.add(char_literal14);


                    	    param=(Token)match(input,ID,FOLLOW_ID_in_inlineFunction281); if (state.failed) return retval; 
                    	    if ( state.backtracking==0 ) stream_ID.add(param);


                    	    if ( state.backtracking==0 ) { params.add(param); }

                    	    }
                    	    break;

                    	default :
                    	    break loop4;
                        }
                    } while (true);


                    }
                    break;

            }


            char_literal15=(Token)match(input,40,FOLLOW_40_in_inlineFunction292); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_40.add(char_literal15);


            if ( state.backtracking==0 ) { 
                addConstantScope();
                for(int index = 0; index < params.size(); index++) 
                  this.getConstantRegistry().put(params.get(index).getText(), new InputSelection(index)); 
              }

            pushFollow(FOLLOW_contextAwareExpression_in_inlineFunction304);
            def=contextAwareExpression(null);

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) stream_contextAwareExpression.add(def.getTree());

            if ( state.backtracking==0 ) { 
                retval.func = new ExpressionFunction(params.size(), def.tree);
                removeConstantScope(); 
              }

            // AST REWRITE
            // elements: 
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            if ( state.backtracking==0 ) {

            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

            root_0 = (EvaluationExpression)adaptor.nil();
            // 90:5: ->
            {
                root_0 = null;
            }


            retval.tree = root_0;
            }

            }

            retval.stop = input.LT(-1);


            if ( state.backtracking==0 ) {

            retval.tree = (EvaluationExpression)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }

        catch (RecognitionException e) {
          throw e;
        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "inlineFunction"


    public static class javaudf_return extends ParserRuleReturnScope {
        EvaluationExpression tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "javaudf"
    // /home/arv/workspace/master/meteor/meteor-meteor/src/main/java/eu/stratosphere/meteor/Meteor.g:92:1: javaudf : name= ID '=' JAVAUDF '(' path= STRING ')' ->;
    public final MeteorParser.javaudf_return javaudf() throws RecognitionException {
        MeteorParser.javaudf_return retval = new MeteorParser.javaudf_return();
        retval.start = input.LT(1);


        EvaluationExpression root_0 = null;

        Token name=null;
        Token path=null;
        Token char_literal16=null;
        Token JAVAUDF17=null;
        Token char_literal18=null;
        Token char_literal19=null;

        EvaluationExpression name_tree=null;
        EvaluationExpression path_tree=null;
        EvaluationExpression char_literal16_tree=null;
        EvaluationExpression JAVAUDF17_tree=null;
        EvaluationExpression char_literal18_tree=null;
        EvaluationExpression char_literal19_tree=null;
        RewriteRuleTokenStream stream_40=new RewriteRuleTokenStream(adaptor,"token 40");
        RewriteRuleTokenStream stream_ID=new RewriteRuleTokenStream(adaptor,"token ID");
        RewriteRuleTokenStream stream_52=new RewriteRuleTokenStream(adaptor,"token 52");
        RewriteRuleTokenStream stream_JAVAUDF=new RewriteRuleTokenStream(adaptor,"token JAVAUDF");
        RewriteRuleTokenStream stream_39=new RewriteRuleTokenStream(adaptor,"token 39");
        RewriteRuleTokenStream stream_STRING=new RewriteRuleTokenStream(adaptor,"token STRING");

        try {
            // /home/arv/workspace/master/meteor/meteor-meteor/src/main/java/eu/stratosphere/meteor/Meteor.g:93:3: (name= ID '=' JAVAUDF '(' path= STRING ')' ->)
            // /home/arv/workspace/master/meteor/meteor-meteor/src/main/java/eu/stratosphere/meteor/Meteor.g:93:5: name= ID '=' JAVAUDF '(' path= STRING ')'
            {
            name=(Token)match(input,ID,FOLLOW_ID_in_javaudf326); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_ID.add(name);


            char_literal16=(Token)match(input,52,FOLLOW_52_in_javaudf328); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_52.add(char_literal16);


            JAVAUDF17=(Token)match(input,JAVAUDF,FOLLOW_JAVAUDF_in_javaudf330); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_JAVAUDF.add(JAVAUDF17);


            char_literal18=(Token)match(input,39,FOLLOW_39_in_javaudf332); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_39.add(char_literal18);


            path=(Token)match(input,STRING,FOLLOW_STRING_in_javaudf336); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_STRING.add(path);


            char_literal19=(Token)match(input,40,FOLLOW_40_in_javaudf338); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_40.add(char_literal19);


            if ( state.backtracking==0 ) { addFunction(name.getText(), path.getText()); }

            // AST REWRITE
            // elements: 
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            if ( state.backtracking==0 ) {

            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

            root_0 = (EvaluationExpression)adaptor.nil();
            // 94:53: ->
            {
                root_0 = null;
            }


            retval.tree = root_0;
            }

            }

            retval.stop = input.LT(-1);


            if ( state.backtracking==0 ) {

            retval.tree = (EvaluationExpression)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }

        catch (RecognitionException e) {
          throw e;
        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "javaudf"


    protected static class contextAwareExpression_scope {
        EvaluationExpression context;
    }
    protected Stack contextAwareExpression_stack = new Stack();


    public static class contextAwareExpression_return extends ParserRuleReturnScope {
        EvaluationExpression tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "contextAwareExpression"
    // /home/arv/workspace/master/meteor/meteor-meteor/src/main/java/eu/stratosphere/meteor/Meteor.g:96:1: contextAwareExpression[EvaluationExpression contextExpression] : ternaryExpression ;
    public final MeteorParser.contextAwareExpression_return contextAwareExpression(EvaluationExpression contextExpression) throws RecognitionException {
        contextAwareExpression_stack.push(new contextAwareExpression_scope());
        MeteorParser.contextAwareExpression_return retval = new MeteorParser.contextAwareExpression_return();
        retval.start = input.LT(1);


        EvaluationExpression root_0 = null;

        MeteorParser.ternaryExpression_return ternaryExpression20 =null;



         ((contextAwareExpression_scope)contextAwareExpression_stack.peek()).context = contextExpression; 
        try {
            // /home/arv/workspace/master/meteor/meteor-meteor/src/main/java/eu/stratosphere/meteor/Meteor.g:99:3: ( ternaryExpression )
            // /home/arv/workspace/master/meteor/meteor-meteor/src/main/java/eu/stratosphere/meteor/Meteor.g:99:5: ternaryExpression
            {
            root_0 = (EvaluationExpression)adaptor.nil();


            pushFollow(FOLLOW_ternaryExpression_in_contextAwareExpression366);
            ternaryExpression20=ternaryExpression();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, ternaryExpression20.getTree());

            }

            retval.stop = input.LT(-1);


            if ( state.backtracking==0 ) {

            retval.tree = (EvaluationExpression)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }

        catch (RecognitionException e) {
          throw e;
        }

        finally {
        	// do for sure before leaving
            contextAwareExpression_stack.pop();
        }
        return retval;
    }
    // $ANTLR end "contextAwareExpression"


    public static class expression_return extends ParserRuleReturnScope {
        EvaluationExpression tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "expression"
    // /home/arv/workspace/master/meteor/meteor-meteor/src/main/java/eu/stratosphere/meteor/Meteor.g:101:1: expression : ( ( operatorExpression )=> operatorExpression | ternaryExpression );
    public final MeteorParser.expression_return expression() throws RecognitionException {
        MeteorParser.expression_return retval = new MeteorParser.expression_return();
        retval.start = input.LT(1);


        EvaluationExpression root_0 = null;

        MeteorParser.operatorExpression_return operatorExpression21 =null;

        MeteorParser.ternaryExpression_return ternaryExpression22 =null;



        try {
            // /home/arv/workspace/master/meteor/meteor-meteor/src/main/java/eu/stratosphere/meteor/Meteor.g:102:3: ( ( operatorExpression )=> operatorExpression | ternaryExpression )
            int alt6=2;
            int LA6_0 = input.LA(1);

            if ( (LA6_0==63) && (synpred1_Meteor())) {
                alt6=1;
            }
            else if ( (LA6_0==67) && (synpred1_Meteor())) {
                alt6=1;
            }
            else if ( (LA6_0==ID) ) {
                int LA6_3 = input.LA(2);

                if ( (LA6_3==48) ) {
                    int LA6_5 = input.LA(3);

                    if ( (LA6_5==ID) ) {
                        int LA6_9 = input.LA(4);

                        if ( (LA6_9==AND||LA6_9==AS||(LA6_9 >= IF && LA6_9 <= IN)||LA6_9==NOT||LA6_9==OR||LA6_9==STAR||(LA6_9 >= 36 && LA6_9 <= 37)||(LA6_9 >= 39 && LA6_9 <= 41)||(LA6_9 >= 43 && LA6_9 <= 44)||(LA6_9 >= 46 && LA6_9 <= 47)||(LA6_9 >= 50 && LA6_9 <= 51)||(LA6_9 >= 53 && LA6_9 <= 57)||LA6_9==59||(LA6_9 >= 69 && LA6_9 <= 70)) ) {
                            alt6=2;
                        }
                        else if ( (LA6_9==ID) && (synpred1_Meteor())) {
                            alt6=1;
                        }
                        else if ( (LA6_9==58) ) {
                            int LA6_7 = input.LA(5);

                            if ( (LA6_7==VAR) && (synpred1_Meteor())) {
                                alt6=1;
                            }
                            else if ( (LA6_7==INTEGER||LA6_7==STAR||LA6_7==UINT) ) {
                                alt6=2;
                            }
                            else {
                                if (state.backtracking>0) {state.failed=true; return retval;}
                                NoViableAltException nvae =
                                    new NoViableAltException("", 6, 7, input);

                                throw nvae;

                            }
                        }
                        else if ( (LA6_9==VAR) && (synpred1_Meteor())) {
                            alt6=1;
                        }
                        else {
                            if (state.backtracking>0) {state.failed=true; return retval;}
                            NoViableAltException nvae =
                                new NoViableAltException("", 6, 9, input);

                            throw nvae;

                        }
                    }
                    else {
                        if (state.backtracking>0) {state.failed=true; return retval;}
                        NoViableAltException nvae =
                            new NoViableAltException("", 6, 5, input);

                        throw nvae;

                    }
                }
                else if ( (LA6_3==AND||LA6_3==AS||(LA6_3 >= IF && LA6_3 <= IN)||LA6_3==NOT||LA6_3==OR||LA6_3==STAR||(LA6_3 >= 36 && LA6_3 <= 37)||(LA6_3 >= 39 && LA6_3 <= 41)||(LA6_3 >= 43 && LA6_3 <= 44)||(LA6_3 >= 46 && LA6_3 <= 47)||(LA6_3 >= 50 && LA6_3 <= 51)||(LA6_3 >= 53 && LA6_3 <= 57)||LA6_3==59||(LA6_3 >= 69 && LA6_3 <= 70)) ) {
                    alt6=2;
                }
                else if ( (LA6_3==ID) && (synpred1_Meteor())) {
                    alt6=1;
                }
                else if ( (LA6_3==58) ) {
                    int LA6_7 = input.LA(3);

                    if ( (LA6_7==VAR) && (synpred1_Meteor())) {
                        alt6=1;
                    }
                    else if ( (LA6_7==INTEGER||LA6_7==STAR||LA6_7==UINT) ) {
                        alt6=2;
                    }
                    else {
                        if (state.backtracking>0) {state.failed=true; return retval;}
                        NoViableAltException nvae =
                            new NoViableAltException("", 6, 7, input);

                        throw nvae;

                    }
                }
                else if ( (LA6_3==VAR) && (synpred1_Meteor())) {
                    alt6=1;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return retval;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 6, 3, input);

                    throw nvae;

                }
            }
            else if ( (LA6_0==DECIMAL||LA6_0==INTEGER||(LA6_0 >= STRING && LA6_0 <= UINT)||LA6_0==VAR||LA6_0==35||LA6_0==39||LA6_0==42||LA6_0==45||LA6_0==58||LA6_0==60||LA6_0==62||LA6_0==65||LA6_0==68||LA6_0==71) ) {
                alt6=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("", 6, 0, input);

                throw nvae;

            }
            switch (alt6) {
                case 1 :
                    // /home/arv/workspace/master/meteor/meteor-meteor/src/main/java/eu/stratosphere/meteor/Meteor.g:102:5: ( operatorExpression )=> operatorExpression
                    {
                    root_0 = (EvaluationExpression)adaptor.nil();


                    pushFollow(FOLLOW_operatorExpression_in_expression381);
                    operatorExpression21=operatorExpression();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, operatorExpression21.getTree());

                    }
                    break;
                case 2 :
                    // /home/arv/workspace/master/meteor/meteor-meteor/src/main/java/eu/stratosphere/meteor/Meteor.g:103:5: ternaryExpression
                    {
                    root_0 = (EvaluationExpression)adaptor.nil();


                    pushFollow(FOLLOW_ternaryExpression_in_expression387);
                    ternaryExpression22=ternaryExpression();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, ternaryExpression22.getTree());

                    }
                    break;

            }
            retval.stop = input.LT(-1);


            if ( state.backtracking==0 ) {

            retval.tree = (EvaluationExpression)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }

        catch (RecognitionException e) {
          throw e;
        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "expression"


    public static class ternaryExpression_return extends ParserRuleReturnScope {
        EvaluationExpression tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "ternaryExpression"
    // /home/arv/workspace/master/meteor/meteor-meteor/src/main/java/eu/stratosphere/meteor/Meteor.g:105:1: ternaryExpression : ( ( orExpression '?' )=>ifClause= orExpression '?' ifExpr= orExpression ':' elseExpr= orExpression -> ^( EXPRESSION[\"TernaryExpression\"] $ifClause) | ( orExpression IF )=>ifExpr2= orExpression IF ifClause2= orExpression -> ^( EXPRESSION[\"TernaryExpression\"] $ifClause2 $ifExpr2) | orExpression );
    public final MeteorParser.ternaryExpression_return ternaryExpression() throws RecognitionException {
        MeteorParser.ternaryExpression_return retval = new MeteorParser.ternaryExpression_return();
        retval.start = input.LT(1);


        EvaluationExpression root_0 = null;

        Token char_literal23=null;
        Token char_literal24=null;
        Token IF25=null;
        MeteorParser.orExpression_return ifClause =null;

        MeteorParser.orExpression_return ifExpr =null;

        MeteorParser.orExpression_return elseExpr =null;

        MeteorParser.orExpression_return ifExpr2 =null;

        MeteorParser.orExpression_return ifClause2 =null;

        MeteorParser.orExpression_return orExpression26 =null;


        EvaluationExpression char_literal23_tree=null;
        EvaluationExpression char_literal24_tree=null;
        EvaluationExpression IF25_tree=null;
        RewriteRuleTokenStream stream_48=new RewriteRuleTokenStream(adaptor,"token 48");
        RewriteRuleTokenStream stream_56=new RewriteRuleTokenStream(adaptor,"token 56");
        RewriteRuleTokenStream stream_IF=new RewriteRuleTokenStream(adaptor,"token IF");
        RewriteRuleSubtreeStream stream_orExpression=new RewriteRuleSubtreeStream(adaptor,"rule orExpression");
        try {
            // /home/arv/workspace/master/meteor/meteor-meteor/src/main/java/eu/stratosphere/meteor/Meteor.g:106:2: ( ( orExpression '?' )=>ifClause= orExpression '?' ifExpr= orExpression ':' elseExpr= orExpression -> ^( EXPRESSION[\"TernaryExpression\"] $ifClause) | ( orExpression IF )=>ifExpr2= orExpression IF ifClause2= orExpression -> ^( EXPRESSION[\"TernaryExpression\"] $ifClause2 $ifExpr2) | orExpression )
            int alt7=3;
            switch ( input.LA(1) ) {
            case 42:
                {
                int LA7_1 = input.LA(2);

                if ( (synpred2_Meteor()) ) {
                    alt7=1;
                }
                else if ( (synpred3_Meteor()) ) {
                    alt7=2;
                }
                else if ( (true) ) {
                    alt7=3;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return retval;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 7, 1, input);

                    throw nvae;

                }
                }
                break;
            case 45:
                {
                int LA7_2 = input.LA(2);

                if ( (synpred2_Meteor()) ) {
                    alt7=1;
                }
                else if ( (synpred3_Meteor()) ) {
                    alt7=2;
                }
                else if ( (true) ) {
                    alt7=3;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return retval;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 7, 2, input);

                    throw nvae;

                }
                }
                break;
            case 35:
            case 71:
                {
                int LA7_3 = input.LA(2);

                if ( (synpred2_Meteor()) ) {
                    alt7=1;
                }
                else if ( (synpred3_Meteor()) ) {
                    alt7=2;
                }
                else if ( (true) ) {
                    alt7=3;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return retval;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 7, 3, input);

                    throw nvae;

                }
                }
                break;
            case 39:
                {
                int LA7_4 = input.LA(2);

                if ( (synpred2_Meteor()) ) {
                    alt7=1;
                }
                else if ( (synpred3_Meteor()) ) {
                    alt7=2;
                }
                else if ( (true) ) {
                    alt7=3;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return retval;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 7, 4, input);

                    throw nvae;

                }
                }
                break;
            case ID:
                {
                int LA7_5 = input.LA(2);

                if ( (synpred2_Meteor()) ) {
                    alt7=1;
                }
                else if ( (synpred3_Meteor()) ) {
                    alt7=2;
                }
                else if ( (true) ) {
                    alt7=3;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return retval;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 7, 5, input);

                    throw nvae;

                }
                }
                break;
            case 65:
                {
                int LA7_6 = input.LA(2);

                if ( (synpred2_Meteor()) ) {
                    alt7=1;
                }
                else if ( (synpred3_Meteor()) ) {
                    alt7=2;
                }
                else if ( (true) ) {
                    alt7=3;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return retval;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 7, 6, input);

                    throw nvae;

                }
                }
                break;
            case 60:
                {
                int LA7_7 = input.LA(2);

                if ( (synpred2_Meteor()) ) {
                    alt7=1;
                }
                else if ( (synpred3_Meteor()) ) {
                    alt7=2;
                }
                else if ( (true) ) {
                    alt7=3;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return retval;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 7, 7, input);

                    throw nvae;

                }
                }
                break;
            case DECIMAL:
                {
                int LA7_8 = input.LA(2);

                if ( (synpred2_Meteor()) ) {
                    alt7=1;
                }
                else if ( (synpred3_Meteor()) ) {
                    alt7=2;
                }
                else if ( (true) ) {
                    alt7=3;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return retval;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 7, 8, input);

                    throw nvae;

                }
                }
                break;
            case STRING:
                {
                int LA7_9 = input.LA(2);

                if ( (synpred2_Meteor()) ) {
                    alt7=1;
                }
                else if ( (synpred3_Meteor()) ) {
                    alt7=2;
                }
                else if ( (true) ) {
                    alt7=3;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return retval;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 7, 9, input);

                    throw nvae;

                }
                }
                break;
            case UINT:
                {
                int LA7_10 = input.LA(2);

                if ( (synpred2_Meteor()) ) {
                    alt7=1;
                }
                else if ( (synpred3_Meteor()) ) {
                    alt7=2;
                }
                else if ( (true) ) {
                    alt7=3;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return retval;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 7, 10, input);

                    throw nvae;

                }
                }
                break;
            case INTEGER:
                {
                int LA7_11 = input.LA(2);

                if ( (synpred2_Meteor()) ) {
                    alt7=1;
                }
                else if ( (synpred3_Meteor()) ) {
                    alt7=2;
                }
                else if ( (true) ) {
                    alt7=3;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return retval;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 7, 11, input);

                    throw nvae;

                }
                }
                break;
            case 62:
                {
                int LA7_12 = input.LA(2);

                if ( (synpred2_Meteor()) ) {
                    alt7=1;
                }
                else if ( (synpred3_Meteor()) ) {
                    alt7=2;
                }
                else if ( (true) ) {
                    alt7=3;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return retval;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 7, 12, input);

                    throw nvae;

                }
                }
                break;
            case VAR:
                {
                int LA7_13 = input.LA(2);

                if ( (synpred2_Meteor()) ) {
                    alt7=1;
                }
                else if ( (synpred3_Meteor()) ) {
                    alt7=2;
                }
                else if ( (true) ) {
                    alt7=3;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return retval;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 7, 13, input);

                    throw nvae;

                }
                }
                break;
            case 58:
                {
                int LA7_14 = input.LA(2);

                if ( (synpred2_Meteor()) ) {
                    alt7=1;
                }
                else if ( (synpred3_Meteor()) ) {
                    alt7=2;
                }
                else if ( (true) ) {
                    alt7=3;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return retval;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 7, 14, input);

                    throw nvae;

                }
                }
                break;
            case 68:
                {
                int LA7_15 = input.LA(2);

                if ( (synpred2_Meteor()) ) {
                    alt7=1;
                }
                else if ( (synpred3_Meteor()) ) {
                    alt7=2;
                }
                else if ( (true) ) {
                    alt7=3;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return retval;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 7, 15, input);

                    throw nvae;

                }
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("", 7, 0, input);

                throw nvae;

            }

            switch (alt7) {
                case 1 :
                    // /home/arv/workspace/master/meteor/meteor-meteor/src/main/java/eu/stratosphere/meteor/Meteor.g:106:4: ( orExpression '?' )=>ifClause= orExpression '?' ifExpr= orExpression ':' elseExpr= orExpression
                    {
                    pushFollow(FOLLOW_orExpression_in_ternaryExpression405);
                    ifClause=orExpression();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_orExpression.add(ifClause.getTree());

                    char_literal23=(Token)match(input,56,FOLLOW_56_in_ternaryExpression407); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_56.add(char_literal23);


                    pushFollow(FOLLOW_orExpression_in_ternaryExpression411);
                    ifExpr=orExpression();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_orExpression.add(ifExpr.getTree());

                    char_literal24=(Token)match(input,48,FOLLOW_48_in_ternaryExpression413); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_48.add(char_literal24);


                    pushFollow(FOLLOW_orExpression_in_ternaryExpression417);
                    elseExpr=orExpression();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_orExpression.add(elseExpr.getTree());

                    // AST REWRITE
                    // elements: ifClause
                    // token labels: 
                    // rule labels: retval, ifClause
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    if ( state.backtracking==0 ) {

                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);
                    RewriteRuleSubtreeStream stream_ifClause=new RewriteRuleSubtreeStream(adaptor,"rule ifClause",ifClause!=null?ifClause.tree:null);

                    root_0 = (EvaluationExpression)adaptor.nil();
                    // 107:2: -> ^( EXPRESSION[\"TernaryExpression\"] $ifClause)
                    {
                        // /home/arv/workspace/master/meteor/meteor-meteor/src/main/java/eu/stratosphere/meteor/Meteor.g:107:5: ^( EXPRESSION[\"TernaryExpression\"] $ifClause)
                        {
                        EvaluationExpression root_1 = (EvaluationExpression)adaptor.nil();
                        root_1 = (EvaluationExpression)adaptor.becomeRoot(
                        (EvaluationExpression)adaptor.create(EXPRESSION, "TernaryExpression")
                        , root_1);

                        adaptor.addChild(root_1, stream_ifClause.nextTree());

                        adaptor.addChild(root_1,  ifExpr == null ? EvaluationExpression.VALUE : (ifExpr!=null?((EvaluationExpression)ifExpr.tree):null) );

                        adaptor.addChild(root_1,  (elseExpr!=null?((EvaluationExpression)elseExpr.tree):null) );

                        adaptor.addChild(root_0, root_1);
                        }

                    }


                    retval.tree = root_0;
                    }

                    }
                    break;
                case 2 :
                    // /home/arv/workspace/master/meteor/meteor-meteor/src/main/java/eu/stratosphere/meteor/Meteor.g:108:4: ( orExpression IF )=>ifExpr2= orExpression IF ifClause2= orExpression
                    {
                    pushFollow(FOLLOW_orExpression_in_ternaryExpression446);
                    ifExpr2=orExpression();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_orExpression.add(ifExpr2.getTree());

                    IF25=(Token)match(input,IF,FOLLOW_IF_in_ternaryExpression448); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_IF.add(IF25);


                    pushFollow(FOLLOW_orExpression_in_ternaryExpression452);
                    ifClause2=orExpression();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_orExpression.add(ifClause2.getTree());

                    // AST REWRITE
                    // elements: ifClause2, ifExpr2
                    // token labels: 
                    // rule labels: retval, ifExpr2, ifClause2
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    if ( state.backtracking==0 ) {

                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);
                    RewriteRuleSubtreeStream stream_ifExpr2=new RewriteRuleSubtreeStream(adaptor,"rule ifExpr2",ifExpr2!=null?ifExpr2.tree:null);
                    RewriteRuleSubtreeStream stream_ifClause2=new RewriteRuleSubtreeStream(adaptor,"rule ifClause2",ifClause2!=null?ifClause2.tree:null);

                    root_0 = (EvaluationExpression)adaptor.nil();
                    // 109:3: -> ^( EXPRESSION[\"TernaryExpression\"] $ifClause2 $ifExpr2)
                    {
                        // /home/arv/workspace/master/meteor/meteor-meteor/src/main/java/eu/stratosphere/meteor/Meteor.g:109:6: ^( EXPRESSION[\"TernaryExpression\"] $ifClause2 $ifExpr2)
                        {
                        EvaluationExpression root_1 = (EvaluationExpression)adaptor.nil();
                        root_1 = (EvaluationExpression)adaptor.becomeRoot(
                        (EvaluationExpression)adaptor.create(EXPRESSION, "TernaryExpression")
                        , root_1);

                        adaptor.addChild(root_1, stream_ifClause2.nextTree());

                        adaptor.addChild(root_1, stream_ifExpr2.nextTree());

                        adaptor.addChild(root_1,  EvaluationExpression.VALUE );

                        adaptor.addChild(root_0, root_1);
                        }

                    }


                    retval.tree = root_0;
                    }

                    }
                    break;
                case 3 :
                    // /home/arv/workspace/master/meteor/meteor-meteor/src/main/java/eu/stratosphere/meteor/Meteor.g:110:5: orExpression
                    {
                    root_0 = (EvaluationExpression)adaptor.nil();


                    pushFollow(FOLLOW_orExpression_in_ternaryExpression475);
                    orExpression26=orExpression();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, orExpression26.getTree());

                    }
                    break;

            }
            retval.stop = input.LT(-1);


            if ( state.backtracking==0 ) {

            retval.tree = (EvaluationExpression)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }

        catch (RecognitionException e) {
          throw e;
        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "ternaryExpression"


    public static class orExpression_return extends ParserRuleReturnScope {
        EvaluationExpression tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "orExpression"
    // /home/arv/workspace/master/meteor/meteor-meteor/src/main/java/eu/stratosphere/meteor/Meteor.g:112:1: orExpression :exprs+= andExpression ( ( OR | '||' ) exprs+= andExpression )* -> { $exprs.size() == 1 }? ->;
    public final MeteorParser.orExpression_return orExpression() throws RecognitionException {
        MeteorParser.orExpression_return retval = new MeteorParser.orExpression_return();
        retval.start = input.LT(1);


        EvaluationExpression root_0 = null;

        Token OR27=null;
        Token string_literal28=null;
        List list_exprs=null;
        RuleReturnScope exprs = null;
        EvaluationExpression OR27_tree=null;
        EvaluationExpression string_literal28_tree=null;
        RewriteRuleTokenStream stream_69=new RewriteRuleTokenStream(adaptor,"token 69");
        RewriteRuleTokenStream stream_OR=new RewriteRuleTokenStream(adaptor,"token OR");
        RewriteRuleSubtreeStream stream_andExpression=new RewriteRuleSubtreeStream(adaptor,"rule andExpression");
        try {
            // /home/arv/workspace/master/meteor/meteor-meteor/src/main/java/eu/stratosphere/meteor/Meteor.g:113:3: (exprs+= andExpression ( ( OR | '||' ) exprs+= andExpression )* -> { $exprs.size() == 1 }? ->)
            // /home/arv/workspace/master/meteor/meteor-meteor/src/main/java/eu/stratosphere/meteor/Meteor.g:113:5: exprs+= andExpression ( ( OR | '||' ) exprs+= andExpression )*
            {
            pushFollow(FOLLOW_andExpression_in_orExpression488);
            exprs=andExpression();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) stream_andExpression.add(exprs.getTree());
            if (list_exprs==null) list_exprs=new ArrayList();
            list_exprs.add(exprs.getTree());


            // /home/arv/workspace/master/meteor/meteor-meteor/src/main/java/eu/stratosphere/meteor/Meteor.g:113:26: ( ( OR | '||' ) exprs+= andExpression )*
            loop9:
            do {
                int alt9=2;
                int LA9_0 = input.LA(1);

                if ( (LA9_0==OR||LA9_0==69) ) {
                    alt9=1;
                }


                switch (alt9) {
            	case 1 :
            	    // /home/arv/workspace/master/meteor/meteor-meteor/src/main/java/eu/stratosphere/meteor/Meteor.g:113:27: ( OR | '||' ) exprs+= andExpression
            	    {
            	    // /home/arv/workspace/master/meteor/meteor-meteor/src/main/java/eu/stratosphere/meteor/Meteor.g:113:27: ( OR | '||' )
            	    int alt8=2;
            	    int LA8_0 = input.LA(1);

            	    if ( (LA8_0==OR) ) {
            	        alt8=1;
            	    }
            	    else if ( (LA8_0==69) ) {
            	        alt8=2;
            	    }
            	    else {
            	        if (state.backtracking>0) {state.failed=true; return retval;}
            	        NoViableAltException nvae =
            	            new NoViableAltException("", 8, 0, input);

            	        throw nvae;

            	    }
            	    switch (alt8) {
            	        case 1 :
            	            // /home/arv/workspace/master/meteor/meteor-meteor/src/main/java/eu/stratosphere/meteor/Meteor.g:113:28: OR
            	            {
            	            OR27=(Token)match(input,OR,FOLLOW_OR_in_orExpression492); if (state.failed) return retval; 
            	            if ( state.backtracking==0 ) stream_OR.add(OR27);


            	            }
            	            break;
            	        case 2 :
            	            // /home/arv/workspace/master/meteor/meteor-meteor/src/main/java/eu/stratosphere/meteor/Meteor.g:113:33: '||'
            	            {
            	            string_literal28=(Token)match(input,69,FOLLOW_69_in_orExpression496); if (state.failed) return retval; 
            	            if ( state.backtracking==0 ) stream_69.add(string_literal28);


            	            }
            	            break;

            	    }


            	    pushFollow(FOLLOW_andExpression_in_orExpression501);
            	    exprs=andExpression();

            	    state._fsp--;
            	    if (state.failed) return retval;
            	    if ( state.backtracking==0 ) stream_andExpression.add(exprs.getTree());
            	    if (list_exprs==null) list_exprs=new ArrayList();
            	    list_exprs.add(exprs.getTree());


            	    }
            	    break;

            	default :
            	    break loop9;
                }
            } while (true);


            // AST REWRITE
            // elements: 
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            if ( state.backtracking==0 ) {

            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

            root_0 = (EvaluationExpression)adaptor.nil();
            // 114:3: -> { $exprs.size() == 1 }?
            if ( list_exprs.size() == 1 ) {
                adaptor.addChild(root_0,  list_exprs.get(0) );

            }

            else // 115:3: ->
            {
                adaptor.addChild(root_0,  OrExpression.valueOf(list_exprs) );

            }


            retval.tree = root_0;
            }

            }

            retval.stop = input.LT(-1);


            if ( state.backtracking==0 ) {

            retval.tree = (EvaluationExpression)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }

        catch (RecognitionException e) {
          throw e;
        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "orExpression"


    public static class andExpression_return extends ParserRuleReturnScope {
        EvaluationExpression tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "andExpression"
    // /home/arv/workspace/master/meteor/meteor-meteor/src/main/java/eu/stratosphere/meteor/Meteor.g:117:1: andExpression :exprs+= elementExpression ( ( AND | '&&' ) exprs+= elementExpression )* -> { $exprs.size() == 1 }? ->;
    public final MeteorParser.andExpression_return andExpression() throws RecognitionException {
        MeteorParser.andExpression_return retval = new MeteorParser.andExpression_return();
        retval.start = input.LT(1);


        EvaluationExpression root_0 = null;

        Token AND29=null;
        Token string_literal30=null;
        List list_exprs=null;
        RuleReturnScope exprs = null;
        EvaluationExpression AND29_tree=null;
        EvaluationExpression string_literal30_tree=null;
        RewriteRuleTokenStream stream_AND=new RewriteRuleTokenStream(adaptor,"token AND");
        RewriteRuleTokenStream stream_37=new RewriteRuleTokenStream(adaptor,"token 37");
        RewriteRuleSubtreeStream stream_elementExpression=new RewriteRuleSubtreeStream(adaptor,"rule elementExpression");
        try {
            // /home/arv/workspace/master/meteor/meteor-meteor/src/main/java/eu/stratosphere/meteor/Meteor.g:118:3: (exprs+= elementExpression ( ( AND | '&&' ) exprs+= elementExpression )* -> { $exprs.size() == 1 }? ->)
            // /home/arv/workspace/master/meteor/meteor-meteor/src/main/java/eu/stratosphere/meteor/Meteor.g:118:5: exprs+= elementExpression ( ( AND | '&&' ) exprs+= elementExpression )*
            {
            pushFollow(FOLLOW_elementExpression_in_andExpression530);
            exprs=elementExpression();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) stream_elementExpression.add(exprs.getTree());
            if (list_exprs==null) list_exprs=new ArrayList();
            list_exprs.add(exprs.getTree());


            // /home/arv/workspace/master/meteor/meteor-meteor/src/main/java/eu/stratosphere/meteor/Meteor.g:118:30: ( ( AND | '&&' ) exprs+= elementExpression )*
            loop11:
            do {
                int alt11=2;
                int LA11_0 = input.LA(1);

                if ( (LA11_0==AND||LA11_0==37) ) {
                    alt11=1;
                }


                switch (alt11) {
            	case 1 :
            	    // /home/arv/workspace/master/meteor/meteor-meteor/src/main/java/eu/stratosphere/meteor/Meteor.g:118:31: ( AND | '&&' ) exprs+= elementExpression
            	    {
            	    // /home/arv/workspace/master/meteor/meteor-meteor/src/main/java/eu/stratosphere/meteor/Meteor.g:118:31: ( AND | '&&' )
            	    int alt10=2;
            	    int LA10_0 = input.LA(1);

            	    if ( (LA10_0==AND) ) {
            	        alt10=1;
            	    }
            	    else if ( (LA10_0==37) ) {
            	        alt10=2;
            	    }
            	    else {
            	        if (state.backtracking>0) {state.failed=true; return retval;}
            	        NoViableAltException nvae =
            	            new NoViableAltException("", 10, 0, input);

            	        throw nvae;

            	    }
            	    switch (alt10) {
            	        case 1 :
            	            // /home/arv/workspace/master/meteor/meteor-meteor/src/main/java/eu/stratosphere/meteor/Meteor.g:118:32: AND
            	            {
            	            AND29=(Token)match(input,AND,FOLLOW_AND_in_andExpression534); if (state.failed) return retval; 
            	            if ( state.backtracking==0 ) stream_AND.add(AND29);


            	            }
            	            break;
            	        case 2 :
            	            // /home/arv/workspace/master/meteor/meteor-meteor/src/main/java/eu/stratosphere/meteor/Meteor.g:118:38: '&&'
            	            {
            	            string_literal30=(Token)match(input,37,FOLLOW_37_in_andExpression538); if (state.failed) return retval; 
            	            if ( state.backtracking==0 ) stream_37.add(string_literal30);


            	            }
            	            break;

            	    }


            	    pushFollow(FOLLOW_elementExpression_in_andExpression543);
            	    exprs=elementExpression();

            	    state._fsp--;
            	    if (state.failed) return retval;
            	    if ( state.backtracking==0 ) stream_elementExpression.add(exprs.getTree());
            	    if (list_exprs==null) list_exprs=new ArrayList();
            	    list_exprs.add(exprs.getTree());


            	    }
            	    break;

            	default :
            	    break loop11;
                }
            } while (true);


            // AST REWRITE
            // elements: 
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            if ( state.backtracking==0 ) {

            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

            root_0 = (EvaluationExpression)adaptor.nil();
            // 119:3: -> { $exprs.size() == 1 }?
            if ( list_exprs.size() == 1 ) {
                adaptor.addChild(root_0,  list_exprs.get(0) );

            }

            else // 120:3: ->
            {
                adaptor.addChild(root_0,  AndExpression.valueOf(list_exprs) );

            }


            retval.tree = root_0;
            }

            }

            retval.stop = input.LT(-1);


            if ( state.backtracking==0 ) {

            retval.tree = (EvaluationExpression)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }

        catch (RecognitionException e) {
          throw e;
        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "andExpression"


    public static class elementExpression_return extends ParserRuleReturnScope {
        EvaluationExpression tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "elementExpression"
    // /home/arv/workspace/master/meteor/meteor-meteor/src/main/java/eu/stratosphere/meteor/Meteor.g:122:1: elementExpression : elem= comparisonExpression ( (not= NOT )? IN set= comparisonExpression )? -> { set == null }? $elem -> ^( EXPRESSION[\"ElementInSetExpression\"] $elem $set) ;
    public final MeteorParser.elementExpression_return elementExpression() throws RecognitionException {
        MeteorParser.elementExpression_return retval = new MeteorParser.elementExpression_return();
        retval.start = input.LT(1);


        EvaluationExpression root_0 = null;

        Token not=null;
        Token IN31=null;
        MeteorParser.comparisonExpression_return elem =null;

        MeteorParser.comparisonExpression_return set =null;


        EvaluationExpression not_tree=null;
        EvaluationExpression IN31_tree=null;
        RewriteRuleTokenStream stream_IN=new RewriteRuleTokenStream(adaptor,"token IN");
        RewriteRuleTokenStream stream_NOT=new RewriteRuleTokenStream(adaptor,"token NOT");
        RewriteRuleSubtreeStream stream_comparisonExpression=new RewriteRuleSubtreeStream(adaptor,"rule comparisonExpression");
        try {
            // /home/arv/workspace/master/meteor/meteor-meteor/src/main/java/eu/stratosphere/meteor/Meteor.g:123:2: (elem= comparisonExpression ( (not= NOT )? IN set= comparisonExpression )? -> { set == null }? $elem -> ^( EXPRESSION[\"ElementInSetExpression\"] $elem $set) )
            // /home/arv/workspace/master/meteor/meteor-meteor/src/main/java/eu/stratosphere/meteor/Meteor.g:123:4: elem= comparisonExpression ( (not= NOT )? IN set= comparisonExpression )?
            {
            pushFollow(FOLLOW_comparisonExpression_in_elementExpression572);
            elem=comparisonExpression();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) stream_comparisonExpression.add(elem.getTree());

            // /home/arv/workspace/master/meteor/meteor-meteor/src/main/java/eu/stratosphere/meteor/Meteor.g:123:30: ( (not= NOT )? IN set= comparisonExpression )?
            int alt13=2;
            int LA13_0 = input.LA(1);

            if ( (LA13_0==IN||LA13_0==NOT) ) {
                alt13=1;
            }
            switch (alt13) {
                case 1 :
                    // /home/arv/workspace/master/meteor/meteor-meteor/src/main/java/eu/stratosphere/meteor/Meteor.g:123:31: (not= NOT )? IN set= comparisonExpression
                    {
                    // /home/arv/workspace/master/meteor/meteor-meteor/src/main/java/eu/stratosphere/meteor/Meteor.g:123:34: (not= NOT )?
                    int alt12=2;
                    int LA12_0 = input.LA(1);

                    if ( (LA12_0==NOT) ) {
                        alt12=1;
                    }
                    switch (alt12) {
                        case 1 :
                            // /home/arv/workspace/master/meteor/meteor-meteor/src/main/java/eu/stratosphere/meteor/Meteor.g:123:34: not= NOT
                            {
                            not=(Token)match(input,NOT,FOLLOW_NOT_in_elementExpression577); if (state.failed) return retval; 
                            if ( state.backtracking==0 ) stream_NOT.add(not);


                            }
                            break;

                    }


                    IN31=(Token)match(input,IN,FOLLOW_IN_in_elementExpression580); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_IN.add(IN31);


                    pushFollow(FOLLOW_comparisonExpression_in_elementExpression584);
                    set=comparisonExpression();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_comparisonExpression.add(set.getTree());

                    }
                    break;

            }


            // AST REWRITE
            // elements: elem, elem, set
            // token labels: 
            // rule labels: elem, retval, set
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            if ( state.backtracking==0 ) {

            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_elem=new RewriteRuleSubtreeStream(adaptor,"rule elem",elem!=null?elem.tree:null);
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);
            RewriteRuleSubtreeStream stream_set=new RewriteRuleSubtreeStream(adaptor,"rule set",set!=null?set.tree:null);

            root_0 = (EvaluationExpression)adaptor.nil();
            // 124:2: -> { set == null }? $elem
            if ( set == null ) {
                adaptor.addChild(root_0, stream_elem.nextTree());

            }

            else // 125:2: -> ^( EXPRESSION[\"ElementInSetExpression\"] $elem $set)
            {
                // /home/arv/workspace/master/meteor/meteor-meteor/src/main/java/eu/stratosphere/meteor/Meteor.g:125:5: ^( EXPRESSION[\"ElementInSetExpression\"] $elem $set)
                {
                EvaluationExpression root_1 = (EvaluationExpression)adaptor.nil();
                root_1 = (EvaluationExpression)adaptor.becomeRoot(
                (EvaluationExpression)adaptor.create(EXPRESSION, "ElementInSetExpression")
                , root_1);

                adaptor.addChild(root_1, stream_elem.nextTree());

                adaptor.addChild(root_1,  not == null ? ElementInSetExpression.Quantor.EXISTS_IN : ElementInSetExpression.Quantor.EXISTS_NOT_IN);

                adaptor.addChild(root_1, stream_set.nextTree());

                adaptor.addChild(root_0, root_1);
                }

            }


            retval.tree = root_0;
            }

            }

            retval.stop = input.LT(-1);


            if ( state.backtracking==0 ) {

            retval.tree = (EvaluationExpression)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }

        catch (RecognitionException e) {
          throw e;
        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "elementExpression"


    public static class comparisonExpression_return extends ParserRuleReturnScope {
        EvaluationExpression tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "comparisonExpression"
    // /home/arv/workspace/master/meteor/meteor-meteor/src/main/java/eu/stratosphere/meteor/Meteor.g:128:1: comparisonExpression : e1= arithmeticExpression ( (s= '<=' |s= '>=' |s= '<' |s= '>' |s= '==' |s= '!=' ) e2= arithmeticExpression )? -> { $s == null }? $e1 -> { $s.getText().equals(\"!=\") }? ^( EXPRESSION[\"ComparativeExpression\"] $e1 $e2) -> { $s.getText().equals(\"==\") }? ^( EXPRESSION[\"ComparativeExpression\"] $e1 $e2) -> ^( EXPRESSION[\"ComparativeExpression\"] $e1 $e2) ;
    public final MeteorParser.comparisonExpression_return comparisonExpression() throws RecognitionException {
        MeteorParser.comparisonExpression_return retval = new MeteorParser.comparisonExpression_return();
        retval.start = input.LT(1);


        EvaluationExpression root_0 = null;

        Token s=null;
        MeteorParser.arithmeticExpression_return e1 =null;

        MeteorParser.arithmeticExpression_return e2 =null;


        EvaluationExpression s_tree=null;
        RewriteRuleTokenStream stream_55=new RewriteRuleTokenStream(adaptor,"token 55");
        RewriteRuleTokenStream stream_51=new RewriteRuleTokenStream(adaptor,"token 51");
        RewriteRuleTokenStream stream_36=new RewriteRuleTokenStream(adaptor,"token 36");
        RewriteRuleTokenStream stream_53=new RewriteRuleTokenStream(adaptor,"token 53");
        RewriteRuleTokenStream stream_54=new RewriteRuleTokenStream(adaptor,"token 54");
        RewriteRuleTokenStream stream_50=new RewriteRuleTokenStream(adaptor,"token 50");
        RewriteRuleSubtreeStream stream_arithmeticExpression=new RewriteRuleSubtreeStream(adaptor,"rule arithmeticExpression");
        try {
            // /home/arv/workspace/master/meteor/meteor-meteor/src/main/java/eu/stratosphere/meteor/Meteor.g:129:2: (e1= arithmeticExpression ( (s= '<=' |s= '>=' |s= '<' |s= '>' |s= '==' |s= '!=' ) e2= arithmeticExpression )? -> { $s == null }? $e1 -> { $s.getText().equals(\"!=\") }? ^( EXPRESSION[\"ComparativeExpression\"] $e1 $e2) -> { $s.getText().equals(\"==\") }? ^( EXPRESSION[\"ComparativeExpression\"] $e1 $e2) -> ^( EXPRESSION[\"ComparativeExpression\"] $e1 $e2) )
            // /home/arv/workspace/master/meteor/meteor-meteor/src/main/java/eu/stratosphere/meteor/Meteor.g:129:4: e1= arithmeticExpression ( (s= '<=' |s= '>=' |s= '<' |s= '>' |s= '==' |s= '!=' ) e2= arithmeticExpression )?
            {
            pushFollow(FOLLOW_arithmeticExpression_in_comparisonExpression625);
            e1=arithmeticExpression();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) stream_arithmeticExpression.add(e1.getTree());

            // /home/arv/workspace/master/meteor/meteor-meteor/src/main/java/eu/stratosphere/meteor/Meteor.g:129:28: ( (s= '<=' |s= '>=' |s= '<' |s= '>' |s= '==' |s= '!=' ) e2= arithmeticExpression )?
            int alt15=2;
            int LA15_0 = input.LA(1);

            if ( (LA15_0==36||(LA15_0 >= 50 && LA15_0 <= 51)||(LA15_0 >= 53 && LA15_0 <= 55)) ) {
                alt15=1;
            }
            switch (alt15) {
                case 1 :
                    // /home/arv/workspace/master/meteor/meteor-meteor/src/main/java/eu/stratosphere/meteor/Meteor.g:129:29: (s= '<=' |s= '>=' |s= '<' |s= '>' |s= '==' |s= '!=' ) e2= arithmeticExpression
                    {
                    // /home/arv/workspace/master/meteor/meteor-meteor/src/main/java/eu/stratosphere/meteor/Meteor.g:129:29: (s= '<=' |s= '>=' |s= '<' |s= '>' |s= '==' |s= '!=' )
                    int alt14=6;
                    switch ( input.LA(1) ) {
                    case 51:
                        {
                        alt14=1;
                        }
                        break;
                    case 55:
                        {
                        alt14=2;
                        }
                        break;
                    case 50:
                        {
                        alt14=3;
                        }
                        break;
                    case 54:
                        {
                        alt14=4;
                        }
                        break;
                    case 53:
                        {
                        alt14=5;
                        }
                        break;
                    case 36:
                        {
                        alt14=6;
                        }
                        break;
                    default:
                        if (state.backtracking>0) {state.failed=true; return retval;}
                        NoViableAltException nvae =
                            new NoViableAltException("", 14, 0, input);

                        throw nvae;

                    }

                    switch (alt14) {
                        case 1 :
                            // /home/arv/workspace/master/meteor/meteor-meteor/src/main/java/eu/stratosphere/meteor/Meteor.g:129:30: s= '<='
                            {
                            s=(Token)match(input,51,FOLLOW_51_in_comparisonExpression631); if (state.failed) return retval; 
                            if ( state.backtracking==0 ) stream_51.add(s);


                            }
                            break;
                        case 2 :
                            // /home/arv/workspace/master/meteor/meteor-meteor/src/main/java/eu/stratosphere/meteor/Meteor.g:129:39: s= '>='
                            {
                            s=(Token)match(input,55,FOLLOW_55_in_comparisonExpression637); if (state.failed) return retval; 
                            if ( state.backtracking==0 ) stream_55.add(s);


                            }
                            break;
                        case 3 :
                            // /home/arv/workspace/master/meteor/meteor-meteor/src/main/java/eu/stratosphere/meteor/Meteor.g:129:48: s= '<'
                            {
                            s=(Token)match(input,50,FOLLOW_50_in_comparisonExpression643); if (state.failed) return retval; 
                            if ( state.backtracking==0 ) stream_50.add(s);


                            }
                            break;
                        case 4 :
                            // /home/arv/workspace/master/meteor/meteor-meteor/src/main/java/eu/stratosphere/meteor/Meteor.g:129:56: s= '>'
                            {
                            s=(Token)match(input,54,FOLLOW_54_in_comparisonExpression649); if (state.failed) return retval; 
                            if ( state.backtracking==0 ) stream_54.add(s);


                            }
                            break;
                        case 5 :
                            // /home/arv/workspace/master/meteor/meteor-meteor/src/main/java/eu/stratosphere/meteor/Meteor.g:129:64: s= '=='
                            {
                            s=(Token)match(input,53,FOLLOW_53_in_comparisonExpression655); if (state.failed) return retval; 
                            if ( state.backtracking==0 ) stream_53.add(s);


                            }
                            break;
                        case 6 :
                            // /home/arv/workspace/master/meteor/meteor-meteor/src/main/java/eu/stratosphere/meteor/Meteor.g:129:73: s= '!='
                            {
                            s=(Token)match(input,36,FOLLOW_36_in_comparisonExpression661); if (state.failed) return retval; 
                            if ( state.backtracking==0 ) stream_36.add(s);


                            }
                            break;

                    }


                    pushFollow(FOLLOW_arithmeticExpression_in_comparisonExpression666);
                    e2=arithmeticExpression();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_arithmeticExpression.add(e2.getTree());

                    }
                    break;

            }


            // AST REWRITE
            // elements: e2, e1, e2, e2, e1, e1, e1
            // token labels: 
            // rule labels: retval, e1, e2
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            if ( state.backtracking==0 ) {

            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);
            RewriteRuleSubtreeStream stream_e1=new RewriteRuleSubtreeStream(adaptor,"rule e1",e1!=null?e1.tree:null);
            RewriteRuleSubtreeStream stream_e2=new RewriteRuleSubtreeStream(adaptor,"rule e2",e2!=null?e2.tree:null);

            root_0 = (EvaluationExpression)adaptor.nil();
            // 130:2: -> { $s == null }? $e1
            if ( s == null ) {
                adaptor.addChild(root_0, stream_e1.nextTree());

            }

            else // 131:3: -> { $s.getText().equals(\"!=\") }? ^( EXPRESSION[\"ComparativeExpression\"] $e1 $e2)
            if ( s.getText().equals("!=") ) {
                // /home/arv/workspace/master/meteor/meteor-meteor/src/main/java/eu/stratosphere/meteor/Meteor.g:131:38: ^( EXPRESSION[\"ComparativeExpression\"] $e1 $e2)
                {
                EvaluationExpression root_1 = (EvaluationExpression)adaptor.nil();
                root_1 = (EvaluationExpression)adaptor.becomeRoot(
                (EvaluationExpression)adaptor.create(EXPRESSION, "ComparativeExpression")
                , root_1);

                adaptor.addChild(root_1, stream_e1.nextTree());

                adaptor.addChild(root_1, ComparativeExpression.BinaryOperator.NOT_EQUAL);

                adaptor.addChild(root_1, stream_e2.nextTree());

                adaptor.addChild(root_0, root_1);
                }

            }

            else // 132:3: -> { $s.getText().equals(\"==\") }? ^( EXPRESSION[\"ComparativeExpression\"] $e1 $e2)
            if ( s.getText().equals("==") ) {
                // /home/arv/workspace/master/meteor/meteor-meteor/src/main/java/eu/stratosphere/meteor/Meteor.g:132:38: ^( EXPRESSION[\"ComparativeExpression\"] $e1 $e2)
                {
                EvaluationExpression root_1 = (EvaluationExpression)adaptor.nil();
                root_1 = (EvaluationExpression)adaptor.becomeRoot(
                (EvaluationExpression)adaptor.create(EXPRESSION, "ComparativeExpression")
                , root_1);

                adaptor.addChild(root_1, stream_e1.nextTree());

                adaptor.addChild(root_1, ComparativeExpression.BinaryOperator.EQUAL);

                adaptor.addChild(root_1, stream_e2.nextTree());

                adaptor.addChild(root_0, root_1);
                }

            }

            else // 133:2: -> ^( EXPRESSION[\"ComparativeExpression\"] $e1 $e2)
            {
                // /home/arv/workspace/master/meteor/meteor-meteor/src/main/java/eu/stratosphere/meteor/Meteor.g:133:6: ^( EXPRESSION[\"ComparativeExpression\"] $e1 $e2)
                {
                EvaluationExpression root_1 = (EvaluationExpression)adaptor.nil();
                root_1 = (EvaluationExpression)adaptor.becomeRoot(
                (EvaluationExpression)adaptor.create(EXPRESSION, "ComparativeExpression")
                , root_1);

                adaptor.addChild(root_1, stream_e1.nextTree());

                adaptor.addChild(root_1, ComparativeExpression.BinaryOperator.valueOfSymbol((s!=null?s.getText():null)));

                adaptor.addChild(root_1, stream_e2.nextTree());

                adaptor.addChild(root_0, root_1);
                }

            }


            retval.tree = root_0;
            }

            }

            retval.stop = input.LT(-1);


            if ( state.backtracking==0 ) {

            retval.tree = (EvaluationExpression)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }

        catch (RecognitionException e) {
          throw e;
        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "comparisonExpression"


    public static class arithmeticExpression_return extends ParserRuleReturnScope {
        EvaluationExpression tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "arithmeticExpression"
    // /home/arv/workspace/master/meteor/meteor-meteor/src/main/java/eu/stratosphere/meteor/Meteor.g:135:1: arithmeticExpression : e1= multiplicationExpression ( (s= '+' |s= '-' ) e2= multiplicationExpression )? -> { s != null }? ^( EXPRESSION[\"ArithmeticExpression\"] $e1 $e2) -> $e1;
    public final MeteorParser.arithmeticExpression_return arithmeticExpression() throws RecognitionException {
        MeteorParser.arithmeticExpression_return retval = new MeteorParser.arithmeticExpression_return();
        retval.start = input.LT(1);


        EvaluationExpression root_0 = null;

        Token s=null;
        MeteorParser.multiplicationExpression_return e1 =null;

        MeteorParser.multiplicationExpression_return e2 =null;


        EvaluationExpression s_tree=null;
        RewriteRuleTokenStream stream_44=new RewriteRuleTokenStream(adaptor,"token 44");
        RewriteRuleTokenStream stream_41=new RewriteRuleTokenStream(adaptor,"token 41");
        RewriteRuleSubtreeStream stream_multiplicationExpression=new RewriteRuleSubtreeStream(adaptor,"rule multiplicationExpression");
        try {
            // /home/arv/workspace/master/meteor/meteor-meteor/src/main/java/eu/stratosphere/meteor/Meteor.g:136:2: (e1= multiplicationExpression ( (s= '+' |s= '-' ) e2= multiplicationExpression )? -> { s != null }? ^( EXPRESSION[\"ArithmeticExpression\"] $e1 $e2) -> $e1)
            // /home/arv/workspace/master/meteor/meteor-meteor/src/main/java/eu/stratosphere/meteor/Meteor.g:136:4: e1= multiplicationExpression ( (s= '+' |s= '-' ) e2= multiplicationExpression )?
            {
            pushFollow(FOLLOW_multiplicationExpression_in_arithmeticExpression746);
            e1=multiplicationExpression();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) stream_multiplicationExpression.add(e1.getTree());

            // /home/arv/workspace/master/meteor/meteor-meteor/src/main/java/eu/stratosphere/meteor/Meteor.g:136:32: ( (s= '+' |s= '-' ) e2= multiplicationExpression )?
            int alt17=2;
            int LA17_0 = input.LA(1);

            if ( (LA17_0==41||LA17_0==44) ) {
                alt17=1;
            }
            switch (alt17) {
                case 1 :
                    // /home/arv/workspace/master/meteor/meteor-meteor/src/main/java/eu/stratosphere/meteor/Meteor.g:136:33: (s= '+' |s= '-' ) e2= multiplicationExpression
                    {
                    // /home/arv/workspace/master/meteor/meteor-meteor/src/main/java/eu/stratosphere/meteor/Meteor.g:136:33: (s= '+' |s= '-' )
                    int alt16=2;
                    int LA16_0 = input.LA(1);

                    if ( (LA16_0==41) ) {
                        alt16=1;
                    }
                    else if ( (LA16_0==44) ) {
                        alt16=2;
                    }
                    else {
                        if (state.backtracking>0) {state.failed=true; return retval;}
                        NoViableAltException nvae =
                            new NoViableAltException("", 16, 0, input);

                        throw nvae;

                    }
                    switch (alt16) {
                        case 1 :
                            // /home/arv/workspace/master/meteor/meteor-meteor/src/main/java/eu/stratosphere/meteor/Meteor.g:136:34: s= '+'
                            {
                            s=(Token)match(input,41,FOLLOW_41_in_arithmeticExpression752); if (state.failed) return retval; 
                            if ( state.backtracking==0 ) stream_41.add(s);


                            }
                            break;
                        case 2 :
                            // /home/arv/workspace/master/meteor/meteor-meteor/src/main/java/eu/stratosphere/meteor/Meteor.g:136:42: s= '-'
                            {
                            s=(Token)match(input,44,FOLLOW_44_in_arithmeticExpression758); if (state.failed) return retval; 
                            if ( state.backtracking==0 ) stream_44.add(s);


                            }
                            break;

                    }


                    pushFollow(FOLLOW_multiplicationExpression_in_arithmeticExpression763);
                    e2=multiplicationExpression();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_multiplicationExpression.add(e2.getTree());

                    }
                    break;

            }


            // AST REWRITE
            // elements: e2, e1, e1
            // token labels: 
            // rule labels: retval, e1, e2
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            if ( state.backtracking==0 ) {

            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);
            RewriteRuleSubtreeStream stream_e1=new RewriteRuleSubtreeStream(adaptor,"rule e1",e1!=null?e1.tree:null);
            RewriteRuleSubtreeStream stream_e2=new RewriteRuleSubtreeStream(adaptor,"rule e2",e2!=null?e2.tree:null);

            root_0 = (EvaluationExpression)adaptor.nil();
            // 137:2: -> { s != null }? ^( EXPRESSION[\"ArithmeticExpression\"] $e1 $e2)
            if ( s != null ) {
                // /home/arv/workspace/master/meteor/meteor-meteor/src/main/java/eu/stratosphere/meteor/Meteor.g:137:21: ^( EXPRESSION[\"ArithmeticExpression\"] $e1 $e2)
                {
                EvaluationExpression root_1 = (EvaluationExpression)adaptor.nil();
                root_1 = (EvaluationExpression)adaptor.becomeRoot(
                (EvaluationExpression)adaptor.create(EXPRESSION, "ArithmeticExpression")
                , root_1);

                adaptor.addChild(root_1, stream_e1.nextTree());

                adaptor.addChild(root_1,  s.getText().equals("+") ? ArithmeticExpression.ArithmeticOperator.ADDITION : ArithmeticExpression.ArithmeticOperator.SUBTRACTION);

                adaptor.addChild(root_1, stream_e2.nextTree());

                adaptor.addChild(root_0, root_1);
                }

            }

            else // 139:2: -> $e1
            {
                adaptor.addChild(root_0, stream_e1.nextTree());

            }


            retval.tree = root_0;
            }

            }

            retval.stop = input.LT(-1);


            if ( state.backtracking==0 ) {

            retval.tree = (EvaluationExpression)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }

        catch (RecognitionException e) {
          throw e;
        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "arithmeticExpression"


    public static class multiplicationExpression_return extends ParserRuleReturnScope {
        EvaluationExpression tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "multiplicationExpression"
    // /home/arv/workspace/master/meteor/meteor-meteor/src/main/java/eu/stratosphere/meteor/Meteor.g:141:1: multiplicationExpression : e1= preincrementExpression ( (s= '*' |s= '/' ) e2= preincrementExpression )? -> { s != null }? ^( EXPRESSION[\"ArithmeticExpression\"] $e1 $e2) -> $e1;
    public final MeteorParser.multiplicationExpression_return multiplicationExpression() throws RecognitionException {
        MeteorParser.multiplicationExpression_return retval = new MeteorParser.multiplicationExpression_return();
        retval.start = input.LT(1);


        EvaluationExpression root_0 = null;

        Token s=null;
        MeteorParser.preincrementExpression_return e1 =null;

        MeteorParser.preincrementExpression_return e2 =null;


        EvaluationExpression s_tree=null;
        RewriteRuleTokenStream stream_STAR=new RewriteRuleTokenStream(adaptor,"token STAR");
        RewriteRuleTokenStream stream_47=new RewriteRuleTokenStream(adaptor,"token 47");
        RewriteRuleSubtreeStream stream_preincrementExpression=new RewriteRuleSubtreeStream(adaptor,"rule preincrementExpression");
        try {
            // /home/arv/workspace/master/meteor/meteor-meteor/src/main/java/eu/stratosphere/meteor/Meteor.g:142:2: (e1= preincrementExpression ( (s= '*' |s= '/' ) e2= preincrementExpression )? -> { s != null }? ^( EXPRESSION[\"ArithmeticExpression\"] $e1 $e2) -> $e1)
            // /home/arv/workspace/master/meteor/meteor-meteor/src/main/java/eu/stratosphere/meteor/Meteor.g:142:4: e1= preincrementExpression ( (s= '*' |s= '/' ) e2= preincrementExpression )?
            {
            pushFollow(FOLLOW_preincrementExpression_in_multiplicationExpression806);
            e1=preincrementExpression();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) stream_preincrementExpression.add(e1.getTree());

            // /home/arv/workspace/master/meteor/meteor-meteor/src/main/java/eu/stratosphere/meteor/Meteor.g:142:30: ( (s= '*' |s= '/' ) e2= preincrementExpression )?
            int alt19=2;
            int LA19_0 = input.LA(1);

            if ( (LA19_0==STAR||LA19_0==47) ) {
                alt19=1;
            }
            switch (alt19) {
                case 1 :
                    // /home/arv/workspace/master/meteor/meteor-meteor/src/main/java/eu/stratosphere/meteor/Meteor.g:142:31: (s= '*' |s= '/' ) e2= preincrementExpression
                    {
                    // /home/arv/workspace/master/meteor/meteor-meteor/src/main/java/eu/stratosphere/meteor/Meteor.g:142:31: (s= '*' |s= '/' )
                    int alt18=2;
                    int LA18_0 = input.LA(1);

                    if ( (LA18_0==STAR) ) {
                        alt18=1;
                    }
                    else if ( (LA18_0==47) ) {
                        alt18=2;
                    }
                    else {
                        if (state.backtracking>0) {state.failed=true; return retval;}
                        NoViableAltException nvae =
                            new NoViableAltException("", 18, 0, input);

                        throw nvae;

                    }
                    switch (alt18) {
                        case 1 :
                            // /home/arv/workspace/master/meteor/meteor-meteor/src/main/java/eu/stratosphere/meteor/Meteor.g:142:32: s= '*'
                            {
                            s=(Token)match(input,STAR,FOLLOW_STAR_in_multiplicationExpression812); if (state.failed) return retval; 
                            if ( state.backtracking==0 ) stream_STAR.add(s);


                            }
                            break;
                        case 2 :
                            // /home/arv/workspace/master/meteor/meteor-meteor/src/main/java/eu/stratosphere/meteor/Meteor.g:142:40: s= '/'
                            {
                            s=(Token)match(input,47,FOLLOW_47_in_multiplicationExpression818); if (state.failed) return retval; 
                            if ( state.backtracking==0 ) stream_47.add(s);


                            }
                            break;

                    }


                    pushFollow(FOLLOW_preincrementExpression_in_multiplicationExpression823);
                    e2=preincrementExpression();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_preincrementExpression.add(e2.getTree());

                    }
                    break;

            }


            // AST REWRITE
            // elements: e1, e2, e1
            // token labels: 
            // rule labels: retval, e1, e2
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            if ( state.backtracking==0 ) {

            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);
            RewriteRuleSubtreeStream stream_e1=new RewriteRuleSubtreeStream(adaptor,"rule e1",e1!=null?e1.tree:null);
            RewriteRuleSubtreeStream stream_e2=new RewriteRuleSubtreeStream(adaptor,"rule e2",e2!=null?e2.tree:null);

            root_0 = (EvaluationExpression)adaptor.nil();
            // 143:2: -> { s != null }? ^( EXPRESSION[\"ArithmeticExpression\"] $e1 $e2)
            if ( s != null ) {
                // /home/arv/workspace/master/meteor/meteor-meteor/src/main/java/eu/stratosphere/meteor/Meteor.g:143:21: ^( EXPRESSION[\"ArithmeticExpression\"] $e1 $e2)
                {
                EvaluationExpression root_1 = (EvaluationExpression)adaptor.nil();
                root_1 = (EvaluationExpression)adaptor.becomeRoot(
                (EvaluationExpression)adaptor.create(EXPRESSION, "ArithmeticExpression")
                , root_1);

                adaptor.addChild(root_1, stream_e1.nextTree());

                adaptor.addChild(root_1,  s.getText().equals("*") ? ArithmeticExpression.ArithmeticOperator.MULTIPLICATION : ArithmeticExpression.ArithmeticOperator.DIVISION);

                adaptor.addChild(root_1, stream_e2.nextTree());

                adaptor.addChild(root_0, root_1);
                }

            }

            else // 145:2: -> $e1
            {
                adaptor.addChild(root_0, stream_e1.nextTree());

            }


            retval.tree = root_0;
            }

            }

            retval.stop = input.LT(-1);


            if ( state.backtracking==0 ) {

            retval.tree = (EvaluationExpression)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }

        catch (RecognitionException e) {
          throw e;
        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "multiplicationExpression"


    public static class preincrementExpression_return extends ParserRuleReturnScope {
        EvaluationExpression tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "preincrementExpression"
    // /home/arv/workspace/master/meteor/meteor-meteor/src/main/java/eu/stratosphere/meteor/Meteor.g:147:1: preincrementExpression : ( '++' preincrementExpression | '--' preincrementExpression | unaryExpression );
    public final MeteorParser.preincrementExpression_return preincrementExpression() throws RecognitionException {
        MeteorParser.preincrementExpression_return retval = new MeteorParser.preincrementExpression_return();
        retval.start = input.LT(1);


        EvaluationExpression root_0 = null;

        Token string_literal32=null;
        Token string_literal34=null;
        MeteorParser.preincrementExpression_return preincrementExpression33 =null;

        MeteorParser.preincrementExpression_return preincrementExpression35 =null;

        MeteorParser.unaryExpression_return unaryExpression36 =null;


        EvaluationExpression string_literal32_tree=null;
        EvaluationExpression string_literal34_tree=null;

        try {
            // /home/arv/workspace/master/meteor/meteor-meteor/src/main/java/eu/stratosphere/meteor/Meteor.g:148:2: ( '++' preincrementExpression | '--' preincrementExpression | unaryExpression )
            int alt20=3;
            switch ( input.LA(1) ) {
            case 42:
                {
                alt20=1;
                }
                break;
            case 45:
                {
                alt20=2;
                }
                break;
            case DECIMAL:
            case ID:
            case INTEGER:
            case STRING:
            case UINT:
            case VAR:
            case 35:
            case 39:
            case 58:
            case 60:
            case 62:
            case 65:
            case 68:
            case 71:
                {
                alt20=3;
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("", 20, 0, input);

                throw nvae;

            }

            switch (alt20) {
                case 1 :
                    // /home/arv/workspace/master/meteor/meteor-meteor/src/main/java/eu/stratosphere/meteor/Meteor.g:148:4: '++' preincrementExpression
                    {
                    root_0 = (EvaluationExpression)adaptor.nil();


                    string_literal32=(Token)match(input,42,FOLLOW_42_in_preincrementExpression864); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    string_literal32_tree = 
                    (EvaluationExpression)adaptor.create(string_literal32)
                    ;
                    adaptor.addChild(root_0, string_literal32_tree);
                    }

                    pushFollow(FOLLOW_preincrementExpression_in_preincrementExpression866);
                    preincrementExpression33=preincrementExpression();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, preincrementExpression33.getTree());

                    }
                    break;
                case 2 :
                    // /home/arv/workspace/master/meteor/meteor-meteor/src/main/java/eu/stratosphere/meteor/Meteor.g:149:4: '--' preincrementExpression
                    {
                    root_0 = (EvaluationExpression)adaptor.nil();


                    string_literal34=(Token)match(input,45,FOLLOW_45_in_preincrementExpression871); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    string_literal34_tree = 
                    (EvaluationExpression)adaptor.create(string_literal34)
                    ;
                    adaptor.addChild(root_0, string_literal34_tree);
                    }

                    pushFollow(FOLLOW_preincrementExpression_in_preincrementExpression873);
                    preincrementExpression35=preincrementExpression();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, preincrementExpression35.getTree());

                    }
                    break;
                case 3 :
                    // /home/arv/workspace/master/meteor/meteor-meteor/src/main/java/eu/stratosphere/meteor/Meteor.g:150:4: unaryExpression
                    {
                    root_0 = (EvaluationExpression)adaptor.nil();


                    pushFollow(FOLLOW_unaryExpression_in_preincrementExpression878);
                    unaryExpression36=unaryExpression();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, unaryExpression36.getTree());

                    }
                    break;

            }
            retval.stop = input.LT(-1);


            if ( state.backtracking==0 ) {

            retval.tree = (EvaluationExpression)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }

        catch (RecognitionException e) {
          throw e;
        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "preincrementExpression"


    public static class unaryExpression_return extends ParserRuleReturnScope {
        EvaluationExpression tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "unaryExpression"
    // /home/arv/workspace/master/meteor/meteor-meteor/src/main/java/eu/stratosphere/meteor/Meteor.g:152:1: unaryExpression : ( '!' | '~' )? castExpression ;
    public final MeteorParser.unaryExpression_return unaryExpression() throws RecognitionException {
        MeteorParser.unaryExpression_return retval = new MeteorParser.unaryExpression_return();
        retval.start = input.LT(1);


        EvaluationExpression root_0 = null;

        Token set37=null;
        MeteorParser.castExpression_return castExpression38 =null;


        EvaluationExpression set37_tree=null;

        try {
            // /home/arv/workspace/master/meteor/meteor-meteor/src/main/java/eu/stratosphere/meteor/Meteor.g:153:2: ( ( '!' | '~' )? castExpression )
            // /home/arv/workspace/master/meteor/meteor-meteor/src/main/java/eu/stratosphere/meteor/Meteor.g:153:4: ( '!' | '~' )? castExpression
            {
            root_0 = (EvaluationExpression)adaptor.nil();


            // /home/arv/workspace/master/meteor/meteor-meteor/src/main/java/eu/stratosphere/meteor/Meteor.g:153:4: ( '!' | '~' )?
            int alt21=2;
            int LA21_0 = input.LA(1);

            if ( (LA21_0==35||LA21_0==71) ) {
                alt21=1;
            }
            switch (alt21) {
                case 1 :
                    // /home/arv/workspace/master/meteor/meteor-meteor/src/main/java/eu/stratosphere/meteor/Meteor.g:
                    {
                    set37=(Token)input.LT(1);

                    if ( input.LA(1)==35||input.LA(1)==71 ) {
                        input.consume();
                        if ( state.backtracking==0 ) adaptor.addChild(root_0, 
                        (EvaluationExpression)adaptor.create(set37)
                        );
                        state.errorRecovery=false;
                        state.failed=false;
                    }
                    else {
                        if (state.backtracking>0) {state.failed=true; return retval;}
                        MismatchedSetException mse = new MismatchedSetException(null,input);
                        throw mse;
                    }


                    }
                    break;

            }


            pushFollow(FOLLOW_castExpression_in_unaryExpression897);
            castExpression38=castExpression();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, castExpression38.getTree());

            }

            retval.stop = input.LT(-1);


            if ( state.backtracking==0 ) {

            retval.tree = (EvaluationExpression)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }

        catch (RecognitionException e) {
          throw e;
        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "unaryExpression"


    public static class castExpression_return extends ParserRuleReturnScope {
        EvaluationExpression tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "castExpression"
    // /home/arv/workspace/master/meteor/meteor-meteor/src/main/java/eu/stratosphere/meteor/Meteor.g:155:1: castExpression : ( ( '(' ID ')' )=> '(' type= ID ')' expr= generalPathExpression ->| ( generalPathExpression AS )=>expr= generalPathExpression AS type= ID ->| generalPathExpression );
    public final MeteorParser.castExpression_return castExpression() throws RecognitionException {
        MeteorParser.castExpression_return retval = new MeteorParser.castExpression_return();
        retval.start = input.LT(1);


        EvaluationExpression root_0 = null;

        Token type=null;
        Token char_literal39=null;
        Token char_literal40=null;
        Token AS41=null;
        MeteorParser.generalPathExpression_return expr =null;

        MeteorParser.generalPathExpression_return generalPathExpression42 =null;


        EvaluationExpression type_tree=null;
        EvaluationExpression char_literal39_tree=null;
        EvaluationExpression char_literal40_tree=null;
        EvaluationExpression AS41_tree=null;
        RewriteRuleTokenStream stream_AS=new RewriteRuleTokenStream(adaptor,"token AS");
        RewriteRuleTokenStream stream_40=new RewriteRuleTokenStream(adaptor,"token 40");
        RewriteRuleTokenStream stream_ID=new RewriteRuleTokenStream(adaptor,"token ID");
        RewriteRuleTokenStream stream_39=new RewriteRuleTokenStream(adaptor,"token 39");
        RewriteRuleSubtreeStream stream_generalPathExpression=new RewriteRuleSubtreeStream(adaptor,"rule generalPathExpression");
        try {
            // /home/arv/workspace/master/meteor/meteor-meteor/src/main/java/eu/stratosphere/meteor/Meteor.g:156:2: ( ( '(' ID ')' )=> '(' type= ID ')' expr= generalPathExpression ->| ( generalPathExpression AS )=>expr= generalPathExpression AS type= ID ->| generalPathExpression )
            int alt22=3;
            switch ( input.LA(1) ) {
            case 39:
                {
                int LA22_1 = input.LA(2);

                if ( (synpred4_Meteor()) ) {
                    alt22=1;
                }
                else if ( (synpred5_Meteor()) ) {
                    alt22=2;
                }
                else if ( (true) ) {
                    alt22=3;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return retval;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 22, 1, input);

                    throw nvae;

                }
                }
                break;
            case ID:
                {
                int LA22_2 = input.LA(2);

                if ( (synpred5_Meteor()) ) {
                    alt22=2;
                }
                else if ( (true) ) {
                    alt22=3;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return retval;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 22, 2, input);

                    throw nvae;

                }
                }
                break;
            case 65:
                {
                int LA22_3 = input.LA(2);

                if ( (synpred5_Meteor()) ) {
                    alt22=2;
                }
                else if ( (true) ) {
                    alt22=3;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return retval;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 22, 3, input);

                    throw nvae;

                }
                }
                break;
            case 60:
                {
                int LA22_4 = input.LA(2);

                if ( (synpred5_Meteor()) ) {
                    alt22=2;
                }
                else if ( (true) ) {
                    alt22=3;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return retval;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 22, 4, input);

                    throw nvae;

                }
                }
                break;
            case DECIMAL:
                {
                int LA22_5 = input.LA(2);

                if ( (synpred5_Meteor()) ) {
                    alt22=2;
                }
                else if ( (true) ) {
                    alt22=3;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return retval;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 22, 5, input);

                    throw nvae;

                }
                }
                break;
            case STRING:
                {
                int LA22_6 = input.LA(2);

                if ( (synpred5_Meteor()) ) {
                    alt22=2;
                }
                else if ( (true) ) {
                    alt22=3;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return retval;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 22, 6, input);

                    throw nvae;

                }
                }
                break;
            case UINT:
                {
                int LA22_7 = input.LA(2);

                if ( (synpred5_Meteor()) ) {
                    alt22=2;
                }
                else if ( (true) ) {
                    alt22=3;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return retval;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 22, 7, input);

                    throw nvae;

                }
                }
                break;
            case INTEGER:
                {
                int LA22_8 = input.LA(2);

                if ( (synpred5_Meteor()) ) {
                    alt22=2;
                }
                else if ( (true) ) {
                    alt22=3;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return retval;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 22, 8, input);

                    throw nvae;

                }
                }
                break;
            case 62:
                {
                int LA22_9 = input.LA(2);

                if ( (synpred5_Meteor()) ) {
                    alt22=2;
                }
                else if ( (true) ) {
                    alt22=3;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return retval;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 22, 9, input);

                    throw nvae;

                }
                }
                break;
            case VAR:
                {
                int LA22_10 = input.LA(2);

                if ( (synpred5_Meteor()) ) {
                    alt22=2;
                }
                else if ( (true) ) {
                    alt22=3;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return retval;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 22, 10, input);

                    throw nvae;

                }
                }
                break;
            case 58:
                {
                int LA22_11 = input.LA(2);

                if ( (synpred5_Meteor()) ) {
                    alt22=2;
                }
                else if ( (true) ) {
                    alt22=3;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return retval;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 22, 11, input);

                    throw nvae;

                }
                }
                break;
            case 68:
                {
                int LA22_12 = input.LA(2);

                if ( (synpred5_Meteor()) ) {
                    alt22=2;
                }
                else if ( (true) ) {
                    alt22=3;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return retval;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 22, 12, input);

                    throw nvae;

                }
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("", 22, 0, input);

                throw nvae;

            }

            switch (alt22) {
                case 1 :
                    // /home/arv/workspace/master/meteor/meteor-meteor/src/main/java/eu/stratosphere/meteor/Meteor.g:156:4: ( '(' ID ')' )=> '(' type= ID ')' expr= generalPathExpression
                    {
                    char_literal39=(Token)match(input,39,FOLLOW_39_in_castExpression915); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_39.add(char_literal39);


                    type=(Token)match(input,ID,FOLLOW_ID_in_castExpression919); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_ID.add(type);


                    char_literal40=(Token)match(input,40,FOLLOW_40_in_castExpression921); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_40.add(char_literal40);


                    pushFollow(FOLLOW_generalPathExpression_in_castExpression925);
                    expr=generalPathExpression();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_generalPathExpression.add(expr.getTree());

                    // AST REWRITE
                    // elements: 
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    if ( state.backtracking==0 ) {

                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (EvaluationExpression)adaptor.nil();
                    // 157:3: ->
                    {
                        adaptor.addChild(root_0,  coerce((type!=null?type.getText():null), (expr!=null?((EvaluationExpression)expr.tree):null)) );

                    }


                    retval.tree = root_0;
                    }

                    }
                    break;
                case 2 :
                    // /home/arv/workspace/master/meteor/meteor-meteor/src/main/java/eu/stratosphere/meteor/Meteor.g:158:4: ( generalPathExpression AS )=>expr= generalPathExpression AS type= ID
                    {
                    pushFollow(FOLLOW_generalPathExpression_in_castExpression945);
                    expr=generalPathExpression();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_generalPathExpression.add(expr.getTree());

                    AS41=(Token)match(input,AS,FOLLOW_AS_in_castExpression947); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_AS.add(AS41);


                    type=(Token)match(input,ID,FOLLOW_ID_in_castExpression951); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_ID.add(type);


                    // AST REWRITE
                    // elements: 
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    if ( state.backtracking==0 ) {

                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (EvaluationExpression)adaptor.nil();
                    // 159:3: ->
                    {
                        adaptor.addChild(root_0,  coerce((type!=null?type.getText():null), (expr!=null?((EvaluationExpression)expr.tree):null)) );

                    }


                    retval.tree = root_0;
                    }

                    }
                    break;
                case 3 :
                    // /home/arv/workspace/master/meteor/meteor-meteor/src/main/java/eu/stratosphere/meteor/Meteor.g:160:4: generalPathExpression
                    {
                    root_0 = (EvaluationExpression)adaptor.nil();


                    pushFollow(FOLLOW_generalPathExpression_in_castExpression962);
                    generalPathExpression42=generalPathExpression();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, generalPathExpression42.getTree());

                    }
                    break;

            }
            retval.stop = input.LT(-1);


            if ( state.backtracking==0 ) {

            retval.tree = (EvaluationExpression)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }

        catch (RecognitionException e) {
          throw e;
        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "castExpression"


    public static class generalPathExpression_return extends ParserRuleReturnScope {
        EvaluationExpression tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "generalPathExpression"
    // /home/arv/workspace/master/meteor/meteor-meteor/src/main/java/eu/stratosphere/meteor/Meteor.g:162:1: generalPathExpression : value= valueExpression ( ( pathExpression[EvaluationExpression.VALUE] )=>path= pathExpression[$value.tree] -> $path| -> $value) ;
    public final MeteorParser.generalPathExpression_return generalPathExpression() throws RecognitionException {
        MeteorParser.generalPathExpression_return retval = new MeteorParser.generalPathExpression_return();
        retval.start = input.LT(1);


        EvaluationExpression root_0 = null;

        MeteorParser.valueExpression_return value =null;

        MeteorParser.pathExpression_return path =null;


        RewriteRuleSubtreeStream stream_valueExpression=new RewriteRuleSubtreeStream(adaptor,"rule valueExpression");
        RewriteRuleSubtreeStream stream_pathExpression=new RewriteRuleSubtreeStream(adaptor,"rule pathExpression");
        try {
            // /home/arv/workspace/master/meteor/meteor-meteor/src/main/java/eu/stratosphere/meteor/Meteor.g:163:2: (value= valueExpression ( ( pathExpression[EvaluationExpression.VALUE] )=>path= pathExpression[$value.tree] -> $path| -> $value) )
            // /home/arv/workspace/master/meteor/meteor-meteor/src/main/java/eu/stratosphere/meteor/Meteor.g:163:4: value= valueExpression ( ( pathExpression[EvaluationExpression.VALUE] )=>path= pathExpression[$value.tree] -> $path| -> $value)
            {
            pushFollow(FOLLOW_valueExpression_in_generalPathExpression974);
            value=valueExpression();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) stream_valueExpression.add(value.getTree());

            // /home/arv/workspace/master/meteor/meteor-meteor/src/main/java/eu/stratosphere/meteor/Meteor.g:164:4: ( ( pathExpression[EvaluationExpression.VALUE] )=>path= pathExpression[$value.tree] -> $path| -> $value)
            int alt23=2;
            int LA23_0 = input.LA(1);

            if ( (LA23_0==57) && (synpred6_Meteor())) {
                alt23=1;
            }
            else if ( (LA23_0==46) && (synpred6_Meteor())) {
                alt23=1;
            }
            else if ( (LA23_0==58) && (synpred6_Meteor())) {
                alt23=1;
            }
            else if ( (LA23_0==EOF||LA23_0==AND||LA23_0==AS||(LA23_0 >= ID && LA23_0 <= IN)||LA23_0==NOT||LA23_0==OR||LA23_0==STAR||(LA23_0 >= 36 && LA23_0 <= 37)||(LA23_0 >= 40 && LA23_0 <= 41)||(LA23_0 >= 43 && LA23_0 <= 44)||(LA23_0 >= 47 && LA23_0 <= 51)||(LA23_0 >= 53 && LA23_0 <= 56)||LA23_0==59||(LA23_0 >= 69 && LA23_0 <= 70)) ) {
                alt23=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("", 23, 0, input);

                throw nvae;

            }
            switch (alt23) {
                case 1 :
                    // /home/arv/workspace/master/meteor/meteor-meteor/src/main/java/eu/stratosphere/meteor/Meteor.g:164:5: ( pathExpression[EvaluationExpression.VALUE] )=>path= pathExpression[$value.tree]
                    {
                    pushFollow(FOLLOW_pathExpression_in_generalPathExpression989);
                    path=pathExpression((value!=null?((EvaluationExpression)value.tree):null));

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_pathExpression.add(path.getTree());

                    // AST REWRITE
                    // elements: path
                    // token labels: 
                    // rule labels: retval, path
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    if ( state.backtracking==0 ) {

                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);
                    RewriteRuleSubtreeStream stream_path=new RewriteRuleSubtreeStream(adaptor,"rule path",path!=null?path.tree:null);

                    root_0 = (EvaluationExpression)adaptor.nil();
                    // 164:85: -> $path
                    {
                        adaptor.addChild(root_0, stream_path.nextTree());

                    }


                    retval.tree = root_0;
                    }

                    }
                    break;
                case 2 :
                    // /home/arv/workspace/master/meteor/meteor-meteor/src/main/java/eu/stratosphere/meteor/Meteor.g:165:7: 
                    {
                    // AST REWRITE
                    // elements: value
                    // token labels: 
                    // rule labels: retval, value
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    if ( state.backtracking==0 ) {

                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);
                    RewriteRuleSubtreeStream stream_value=new RewriteRuleSubtreeStream(adaptor,"rule value",value!=null?value.tree:null);

                    root_0 = (EvaluationExpression)adaptor.nil();
                    // 165:7: -> $value
                    {
                        adaptor.addChild(root_0, stream_value.nextTree());

                    }


                    retval.tree = root_0;
                    }

                    }
                    break;

            }


            }

            retval.stop = input.LT(-1);


            if ( state.backtracking==0 ) {

            retval.tree = (EvaluationExpression)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }

        catch (RecognitionException e) {
          throw e;
        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "generalPathExpression"


    public static class contextAwarePathExpression_return extends ParserRuleReturnScope {
        EvaluationExpression tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "contextAwarePathExpression"
    // /home/arv/workspace/master/meteor/meteor-meteor/src/main/java/eu/stratosphere/meteor/Meteor.g:167:1: contextAwarePathExpression[EvaluationExpression context] : pathExpression[context] ;
    public final MeteorParser.contextAwarePathExpression_return contextAwarePathExpression(EvaluationExpression context) throws RecognitionException {
        MeteorParser.contextAwarePathExpression_return retval = new MeteorParser.contextAwarePathExpression_return();
        retval.start = input.LT(1);


        EvaluationExpression root_0 = null;

        MeteorParser.pathExpression_return pathExpression43 =null;



        try {
            // /home/arv/workspace/master/meteor/meteor-meteor/src/main/java/eu/stratosphere/meteor/Meteor.g:168:3: ( pathExpression[context] )
            // /home/arv/workspace/master/meteor/meteor-meteor/src/main/java/eu/stratosphere/meteor/Meteor.g:168:5: pathExpression[context]
            {
            root_0 = (EvaluationExpression)adaptor.nil();


            pushFollow(FOLLOW_pathExpression_in_contextAwarePathExpression1018);
            pathExpression43=pathExpression(context);

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, pathExpression43.getTree());

            }

            retval.stop = input.LT(-1);


            if ( state.backtracking==0 ) {

            retval.tree = (EvaluationExpression)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }

        catch (RecognitionException e) {
          throw e;
        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "contextAwarePathExpression"


    public static class pathExpression_return extends ParserRuleReturnScope {
        EvaluationExpression tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "pathExpression"
    // /home/arv/workspace/master/meteor/meteor-meteor/src/main/java/eu/stratosphere/meteor/Meteor.g:170:1: pathExpression[EvaluationExpression inExp] : seg= pathSegment ( ( pathSegment )=>path= pathExpression[$seg.tree] -> $path| -> $seg) ;
    public final MeteorParser.pathExpression_return pathExpression(EvaluationExpression inExp) throws RecognitionException {
        MeteorParser.pathExpression_return retval = new MeteorParser.pathExpression_return();
        retval.start = input.LT(1);


        EvaluationExpression root_0 = null;

        MeteorParser.pathSegment_return seg =null;

        MeteorParser.pathExpression_return path =null;


        RewriteRuleSubtreeStream stream_pathSegment=new RewriteRuleSubtreeStream(adaptor,"rule pathSegment");
        RewriteRuleSubtreeStream stream_pathExpression=new RewriteRuleSubtreeStream(adaptor,"rule pathExpression");
        try {
            // /home/arv/workspace/master/meteor/meteor-meteor/src/main/java/eu/stratosphere/meteor/Meteor.g:171:3: (seg= pathSegment ( ( pathSegment )=>path= pathExpression[$seg.tree] -> $path| -> $seg) )
            // /home/arv/workspace/master/meteor/meteor-meteor/src/main/java/eu/stratosphere/meteor/Meteor.g:171:5: seg= pathSegment ( ( pathSegment )=>path= pathExpression[$seg.tree] -> $path| -> $seg)
            {
            pushFollow(FOLLOW_pathSegment_in_pathExpression1034);
            seg=pathSegment();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) stream_pathSegment.add(seg.getTree());

            if ( state.backtracking==0 ) { ((PathSegmentExpression) seg.getTree()).setInputExpression(inExp); }

            // /home/arv/workspace/master/meteor/meteor-meteor/src/main/java/eu/stratosphere/meteor/Meteor.g:172:3: ( ( pathSegment )=>path= pathExpression[$seg.tree] -> $path| -> $seg)
            int alt24=2;
            switch ( input.LA(1) ) {
            case 57:
                {
                int LA24_1 = input.LA(2);

                if ( (synpred7_Meteor()) ) {
                    alt24=1;
                }
                else if ( (true) ) {
                    alt24=2;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return retval;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 24, 1, input);

                    throw nvae;

                }
                }
                break;
            case 46:
                {
                int LA24_2 = input.LA(2);

                if ( (synpred7_Meteor()) ) {
                    alt24=1;
                }
                else if ( (true) ) {
                    alt24=2;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return retval;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 24, 2, input);

                    throw nvae;

                }
                }
                break;
            case 58:
                {
                int LA24_3 = input.LA(2);

                if ( (synpred7_Meteor()) ) {
                    alt24=1;
                }
                else if ( (true) ) {
                    alt24=2;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return retval;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 24, 3, input);

                    throw nvae;

                }
                }
                break;
            case EOF:
            case AND:
            case AS:
            case ID:
            case IF:
            case IN:
            case NOT:
            case OR:
            case STAR:
            case 36:
            case 37:
            case 40:
            case 41:
            case 43:
            case 44:
            case 47:
            case 48:
            case 49:
            case 50:
            case 51:
            case 53:
            case 54:
            case 55:
            case 56:
            case 59:
            case 69:
            case 70:
                {
                alt24=2;
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("", 24, 0, input);

                throw nvae;

            }

            switch (alt24) {
                case 1 :
                    // /home/arv/workspace/master/meteor/meteor-meteor/src/main/java/eu/stratosphere/meteor/Meteor.g:172:4: ( pathSegment )=>path= pathExpression[$seg.tree]
                    {
                    pushFollow(FOLLOW_pathExpression_in_pathExpression1048);
                    path=pathExpression((seg!=null?((EvaluationExpression)seg.tree):null));

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_pathExpression.add(path.getTree());

                    // AST REWRITE
                    // elements: path
                    // token labels: 
                    // rule labels: retval, path
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    if ( state.backtracking==0 ) {

                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);
                    RewriteRuleSubtreeStream stream_path=new RewriteRuleSubtreeStream(adaptor,"rule path",path!=null?path.tree:null);

                    root_0 = (EvaluationExpression)adaptor.nil();
                    // 172:51: -> $path
                    {
                        adaptor.addChild(root_0, stream_path.nextTree());

                    }


                    retval.tree = root_0;
                    }

                    }
                    break;
                case 2 :
                    // /home/arv/workspace/master/meteor/meteor-meteor/src/main/java/eu/stratosphere/meteor/Meteor.g:173:6: 
                    {
                    // AST REWRITE
                    // elements: seg
                    // token labels: 
                    // rule labels: retval, seg
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    if ( state.backtracking==0 ) {

                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);
                    RewriteRuleSubtreeStream stream_seg=new RewriteRuleSubtreeStream(adaptor,"rule seg",seg!=null?seg.tree:null);

                    root_0 = (EvaluationExpression)adaptor.nil();
                    // 173:6: -> $seg
                    {
                        adaptor.addChild(root_0, stream_seg.nextTree());

                    }


                    retval.tree = root_0;
                    }

                    }
                    break;

            }


            }

            retval.stop = input.LT(-1);


            if ( state.backtracking==0 ) {

            retval.tree = (EvaluationExpression)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }

        catch (RecognitionException e) {
          throw e;
        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "pathExpression"


    public static class pathSegment_return extends ParserRuleReturnScope {
        EvaluationExpression tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "pathSegment"
    // /home/arv/workspace/master/meteor/meteor-meteor/src/main/java/eu/stratosphere/meteor/Meteor.g:175:1: pathSegment : ( ( '?.' )=> '?.' field= ID -> ^( EXPRESSION[\"ObjectAccess\"] ) | ( '.' )=> '.' field= ID -> ^( EXPRESSION[\"ObjectAccess\"] ) | ( '[' )=> arrayAccess );
    public final MeteorParser.pathSegment_return pathSegment() throws RecognitionException {
        MeteorParser.pathSegment_return retval = new MeteorParser.pathSegment_return();
        retval.start = input.LT(1);


        EvaluationExpression root_0 = null;

        Token field=null;
        Token string_literal44=null;
        Token char_literal45=null;
        MeteorParser.arrayAccess_return arrayAccess46 =null;


        EvaluationExpression field_tree=null;
        EvaluationExpression string_literal44_tree=null;
        EvaluationExpression char_literal45_tree=null;
        RewriteRuleTokenStream stream_57=new RewriteRuleTokenStream(adaptor,"token 57");
        RewriteRuleTokenStream stream_46=new RewriteRuleTokenStream(adaptor,"token 46");
        RewriteRuleTokenStream stream_ID=new RewriteRuleTokenStream(adaptor,"token ID");

          paraphrase.push("a path expression"); 
        try {
            // /home/arv/workspace/master/meteor/meteor-meteor/src/main/java/eu/stratosphere/meteor/Meteor.g:178:3: ( ( '?.' )=> '?.' field= ID -> ^( EXPRESSION[\"ObjectAccess\"] ) | ( '.' )=> '.' field= ID -> ^( EXPRESSION[\"ObjectAccess\"] ) | ( '[' )=> arrayAccess )
            int alt25=3;
            int LA25_0 = input.LA(1);

            if ( (LA25_0==57) && (synpred8_Meteor())) {
                alt25=1;
            }
            else if ( (LA25_0==46) && (synpred9_Meteor())) {
                alt25=2;
            }
            else if ( (LA25_0==58) && (synpred10_Meteor())) {
                alt25=3;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("", 25, 0, input);

                throw nvae;

            }
            switch (alt25) {
                case 1 :
                    // /home/arv/workspace/master/meteor/meteor-meteor/src/main/java/eu/stratosphere/meteor/Meteor.g:179:5: ( '?.' )=> '?.' field= ID
                    {
                    string_literal44=(Token)match(input,57,FOLLOW_57_in_pathSegment1095); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_57.add(string_literal44);


                    field=(Token)match(input,ID,FOLLOW_ID_in_pathSegment1099); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_ID.add(field);


                    // AST REWRITE
                    // elements: 
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    if ( state.backtracking==0 ) {

                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (EvaluationExpression)adaptor.nil();
                    // 179:28: -> ^( EXPRESSION[\"ObjectAccess\"] )
                    {
                        // /home/arv/workspace/master/meteor/meteor-meteor/src/main/java/eu/stratosphere/meteor/Meteor.g:179:31: ^( EXPRESSION[\"ObjectAccess\"] )
                        {
                        EvaluationExpression root_1 = (EvaluationExpression)adaptor.nil();
                        root_1 = (EvaluationExpression)adaptor.becomeRoot(
                        (EvaluationExpression)adaptor.create(EXPRESSION, "ObjectAccess")
                        , root_1);

                        adaptor.addChild(root_1, (field!=null?field.getText():null));

                        adaptor.addChild(root_1, true);

                        adaptor.addChild(root_0, root_1);
                        }

                    }


                    retval.tree = root_0;
                    }

                    }
                    break;
                case 2 :
                    // /home/arv/workspace/master/meteor/meteor-meteor/src/main/java/eu/stratosphere/meteor/Meteor.g:180:5: ( '.' )=> '.' field= ID
                    {
                    char_literal45=(Token)match(input,46,FOLLOW_46_in_pathSegment1126); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_46.add(char_literal45);


                    field=(Token)match(input,ID,FOLLOW_ID_in_pathSegment1130); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_ID.add(field);


                    // AST REWRITE
                    // elements: 
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    if ( state.backtracking==0 ) {

                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (EvaluationExpression)adaptor.nil();
                    // 180:27: -> ^( EXPRESSION[\"ObjectAccess\"] )
                    {
                        // /home/arv/workspace/master/meteor/meteor-meteor/src/main/java/eu/stratosphere/meteor/Meteor.g:180:30: ^( EXPRESSION[\"ObjectAccess\"] )
                        {
                        EvaluationExpression root_1 = (EvaluationExpression)adaptor.nil();
                        root_1 = (EvaluationExpression)adaptor.becomeRoot(
                        (EvaluationExpression)adaptor.create(EXPRESSION, "ObjectAccess")
                        , root_1);

                        adaptor.addChild(root_1, (field!=null?field.getText():null));

                        adaptor.addChild(root_0, root_1);
                        }

                    }


                    retval.tree = root_0;
                    }

                    }
                    break;
                case 3 :
                    // /home/arv/workspace/master/meteor/meteor-meteor/src/main/java/eu/stratosphere/meteor/Meteor.g:181:5: ( '[' )=> arrayAccess
                    {
                    root_0 = (EvaluationExpression)adaptor.nil();


                    pushFollow(FOLLOW_arrayAccess_in_pathSegment1155);
                    arrayAccess46=arrayAccess();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, arrayAccess46.getTree());

                    }
                    break;

            }
            retval.stop = input.LT(-1);


            if ( state.backtracking==0 ) {

            retval.tree = (EvaluationExpression)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
            if ( state.backtracking==0 ) { paraphrase.pop(); }
        }

        catch (RecognitionException e) {
          throw e;
        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "pathSegment"


    public static class arrayAccess_return extends ParserRuleReturnScope {
        EvaluationExpression tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "arrayAccess"
    // /home/arv/workspace/master/meteor/meteor-meteor/src/main/java/eu/stratosphere/meteor/Meteor.g:184:1: arrayAccess : ( '[' STAR ']' path= pathExpression[EvaluationExpression.VALUE] -> ^( EXPRESSION[\"ArrayProjection\"] $path) | '[' (pos= INTEGER |pos= UINT ) ']' -> ^( EXPRESSION[\"ArrayAccess\"] ) | '[' (start= INTEGER |start= UINT ) ':' (end= INTEGER |end= UINT ) ']' -> ^( EXPRESSION[\"ArrayAccess\"] ) );
    public final MeteorParser.arrayAccess_return arrayAccess() throws RecognitionException {
        MeteorParser.arrayAccess_return retval = new MeteorParser.arrayAccess_return();
        retval.start = input.LT(1);


        EvaluationExpression root_0 = null;

        Token pos=null;
        Token start=null;
        Token end=null;
        Token char_literal47=null;
        Token STAR48=null;
        Token char_literal49=null;
        Token char_literal50=null;
        Token char_literal51=null;
        Token char_literal52=null;
        Token char_literal53=null;
        Token char_literal54=null;
        MeteorParser.pathExpression_return path =null;


        EvaluationExpression pos_tree=null;
        EvaluationExpression start_tree=null;
        EvaluationExpression end_tree=null;
        EvaluationExpression char_literal47_tree=null;
        EvaluationExpression STAR48_tree=null;
        EvaluationExpression char_literal49_tree=null;
        EvaluationExpression char_literal50_tree=null;
        EvaluationExpression char_literal51_tree=null;
        EvaluationExpression char_literal52_tree=null;
        EvaluationExpression char_literal53_tree=null;
        EvaluationExpression char_literal54_tree=null;
        RewriteRuleTokenStream stream_INTEGER=new RewriteRuleTokenStream(adaptor,"token INTEGER");
        RewriteRuleTokenStream stream_48=new RewriteRuleTokenStream(adaptor,"token 48");
        RewriteRuleTokenStream stream_59=new RewriteRuleTokenStream(adaptor,"token 59");
        RewriteRuleTokenStream stream_58=new RewriteRuleTokenStream(adaptor,"token 58");
        RewriteRuleTokenStream stream_STAR=new RewriteRuleTokenStream(adaptor,"token STAR");
        RewriteRuleTokenStream stream_UINT=new RewriteRuleTokenStream(adaptor,"token UINT");
        RewriteRuleSubtreeStream stream_pathExpression=new RewriteRuleSubtreeStream(adaptor,"rule pathExpression");
        try {
            // /home/arv/workspace/master/meteor/meteor-meteor/src/main/java/eu/stratosphere/meteor/Meteor.g:185:3: ( '[' STAR ']' path= pathExpression[EvaluationExpression.VALUE] -> ^( EXPRESSION[\"ArrayProjection\"] $path) | '[' (pos= INTEGER |pos= UINT ) ']' -> ^( EXPRESSION[\"ArrayAccess\"] ) | '[' (start= INTEGER |start= UINT ) ':' (end= INTEGER |end= UINT ) ']' -> ^( EXPRESSION[\"ArrayAccess\"] ) )
            int alt29=3;
            int LA29_0 = input.LA(1);

            if ( (LA29_0==58) ) {
                switch ( input.LA(2) ) {
                case STAR:
                    {
                    alt29=1;
                    }
                    break;
                case INTEGER:
                    {
                    int LA29_3 = input.LA(3);

                    if ( (LA29_3==59) ) {
                        alt29=2;
                    }
                    else if ( (LA29_3==48) ) {
                        alt29=3;
                    }
                    else {
                        if (state.backtracking>0) {state.failed=true; return retval;}
                        NoViableAltException nvae =
                            new NoViableAltException("", 29, 3, input);

                        throw nvae;

                    }
                    }
                    break;
                case UINT:
                    {
                    int LA29_4 = input.LA(3);

                    if ( (LA29_4==59) ) {
                        alt29=2;
                    }
                    else if ( (LA29_4==48) ) {
                        alt29=3;
                    }
                    else {
                        if (state.backtracking>0) {state.failed=true; return retval;}
                        NoViableAltException nvae =
                            new NoViableAltException("", 29, 4, input);

                        throw nvae;

                    }
                    }
                    break;
                default:
                    if (state.backtracking>0) {state.failed=true; return retval;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 29, 1, input);

                    throw nvae;

                }

            }
            else {
                if (state.backtracking>0) {state.failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("", 29, 0, input);

                throw nvae;

            }
            switch (alt29) {
                case 1 :
                    // /home/arv/workspace/master/meteor/meteor-meteor/src/main/java/eu/stratosphere/meteor/Meteor.g:185:5: '[' STAR ']' path= pathExpression[EvaluationExpression.VALUE]
                    {
                    char_literal47=(Token)match(input,58,FOLLOW_58_in_arrayAccess1166); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_58.add(char_literal47);


                    STAR48=(Token)match(input,STAR,FOLLOW_STAR_in_arrayAccess1168); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_STAR.add(STAR48);


                    char_literal49=(Token)match(input,59,FOLLOW_59_in_arrayAccess1170); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_59.add(char_literal49);


                    pushFollow(FOLLOW_pathExpression_in_arrayAccess1174);
                    path=pathExpression(EvaluationExpression.VALUE);

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_pathExpression.add(path.getTree());

                    // AST REWRITE
                    // elements: path
                    // token labels: 
                    // rule labels: retval, path
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    if ( state.backtracking==0 ) {

                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);
                    RewriteRuleSubtreeStream stream_path=new RewriteRuleSubtreeStream(adaptor,"rule path",path!=null?path.tree:null);

                    root_0 = (EvaluationExpression)adaptor.nil();
                    // 186:3: -> ^( EXPRESSION[\"ArrayProjection\"] $path)
                    {
                        // /home/arv/workspace/master/meteor/meteor-meteor/src/main/java/eu/stratosphere/meteor/Meteor.g:186:6: ^( EXPRESSION[\"ArrayProjection\"] $path)
                        {
                        EvaluationExpression root_1 = (EvaluationExpression)adaptor.nil();
                        root_1 = (EvaluationExpression)adaptor.becomeRoot(
                        (EvaluationExpression)adaptor.create(EXPRESSION, "ArrayProjection")
                        , root_1);

                        adaptor.addChild(root_1, stream_path.nextTree());

                        adaptor.addChild(root_0, root_1);
                        }

                    }


                    retval.tree = root_0;
                    }

                    }
                    break;
                case 2 :
                    // /home/arv/workspace/master/meteor/meteor-meteor/src/main/java/eu/stratosphere/meteor/Meteor.g:187:5: '[' (pos= INTEGER |pos= UINT ) ']'
                    {
                    char_literal50=(Token)match(input,58,FOLLOW_58_in_arrayAccess1195); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_58.add(char_literal50);


                    // /home/arv/workspace/master/meteor/meteor-meteor/src/main/java/eu/stratosphere/meteor/Meteor.g:187:9: (pos= INTEGER |pos= UINT )
                    int alt26=2;
                    int LA26_0 = input.LA(1);

                    if ( (LA26_0==INTEGER) ) {
                        alt26=1;
                    }
                    else if ( (LA26_0==UINT) ) {
                        alt26=2;
                    }
                    else {
                        if (state.backtracking>0) {state.failed=true; return retval;}
                        NoViableAltException nvae =
                            new NoViableAltException("", 26, 0, input);

                        throw nvae;

                    }
                    switch (alt26) {
                        case 1 :
                            // /home/arv/workspace/master/meteor/meteor-meteor/src/main/java/eu/stratosphere/meteor/Meteor.g:187:10: pos= INTEGER
                            {
                            pos=(Token)match(input,INTEGER,FOLLOW_INTEGER_in_arrayAccess1200); if (state.failed) return retval; 
                            if ( state.backtracking==0 ) stream_INTEGER.add(pos);


                            }
                            break;
                        case 2 :
                            // /home/arv/workspace/master/meteor/meteor-meteor/src/main/java/eu/stratosphere/meteor/Meteor.g:187:24: pos= UINT
                            {
                            pos=(Token)match(input,UINT,FOLLOW_UINT_in_arrayAccess1206); if (state.failed) return retval; 
                            if ( state.backtracking==0 ) stream_UINT.add(pos);


                            }
                            break;

                    }


                    char_literal51=(Token)match(input,59,FOLLOW_59_in_arrayAccess1209); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_59.add(char_literal51);


                    // AST REWRITE
                    // elements: 
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    if ( state.backtracking==0 ) {

                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (EvaluationExpression)adaptor.nil();
                    // 188:3: -> ^( EXPRESSION[\"ArrayAccess\"] )
                    {
                        // /home/arv/workspace/master/meteor/meteor-meteor/src/main/java/eu/stratosphere/meteor/Meteor.g:188:6: ^( EXPRESSION[\"ArrayAccess\"] )
                        {
                        EvaluationExpression root_1 = (EvaluationExpression)adaptor.nil();
                        root_1 = (EvaluationExpression)adaptor.becomeRoot(
                        (EvaluationExpression)adaptor.create(EXPRESSION, "ArrayAccess")
                        , root_1);

                        adaptor.addChild(root_1,  Integer.valueOf((pos!=null?pos.getText():null)) );

                        adaptor.addChild(root_0, root_1);
                        }

                    }


                    retval.tree = root_0;
                    }

                    }
                    break;
                case 3 :
                    // /home/arv/workspace/master/meteor/meteor-meteor/src/main/java/eu/stratosphere/meteor/Meteor.g:189:5: '[' (start= INTEGER |start= UINT ) ':' (end= INTEGER |end= UINT ) ']'
                    {
                    char_literal52=(Token)match(input,58,FOLLOW_58_in_arrayAccess1227); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_58.add(char_literal52);


                    // /home/arv/workspace/master/meteor/meteor-meteor/src/main/java/eu/stratosphere/meteor/Meteor.g:189:9: (start= INTEGER |start= UINT )
                    int alt27=2;
                    int LA27_0 = input.LA(1);

                    if ( (LA27_0==INTEGER) ) {
                        alt27=1;
                    }
                    else if ( (LA27_0==UINT) ) {
                        alt27=2;
                    }
                    else {
                        if (state.backtracking>0) {state.failed=true; return retval;}
                        NoViableAltException nvae =
                            new NoViableAltException("", 27, 0, input);

                        throw nvae;

                    }
                    switch (alt27) {
                        case 1 :
                            // /home/arv/workspace/master/meteor/meteor-meteor/src/main/java/eu/stratosphere/meteor/Meteor.g:189:10: start= INTEGER
                            {
                            start=(Token)match(input,INTEGER,FOLLOW_INTEGER_in_arrayAccess1232); if (state.failed) return retval; 
                            if ( state.backtracking==0 ) stream_INTEGER.add(start);


                            }
                            break;
                        case 2 :
                            // /home/arv/workspace/master/meteor/meteor-meteor/src/main/java/eu/stratosphere/meteor/Meteor.g:189:26: start= UINT
                            {
                            start=(Token)match(input,UINT,FOLLOW_UINT_in_arrayAccess1238); if (state.failed) return retval; 
                            if ( state.backtracking==0 ) stream_UINT.add(start);


                            }
                            break;

                    }


                    char_literal53=(Token)match(input,48,FOLLOW_48_in_arrayAccess1241); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_48.add(char_literal53);


                    // /home/arv/workspace/master/meteor/meteor-meteor/src/main/java/eu/stratosphere/meteor/Meteor.g:189:42: (end= INTEGER |end= UINT )
                    int alt28=2;
                    int LA28_0 = input.LA(1);

                    if ( (LA28_0==INTEGER) ) {
                        alt28=1;
                    }
                    else if ( (LA28_0==UINT) ) {
                        alt28=2;
                    }
                    else {
                        if (state.backtracking>0) {state.failed=true; return retval;}
                        NoViableAltException nvae =
                            new NoViableAltException("", 28, 0, input);

                        throw nvae;

                    }
                    switch (alt28) {
                        case 1 :
                            // /home/arv/workspace/master/meteor/meteor-meteor/src/main/java/eu/stratosphere/meteor/Meteor.g:189:43: end= INTEGER
                            {
                            end=(Token)match(input,INTEGER,FOLLOW_INTEGER_in_arrayAccess1246); if (state.failed) return retval; 
                            if ( state.backtracking==0 ) stream_INTEGER.add(end);


                            }
                            break;
                        case 2 :
                            // /home/arv/workspace/master/meteor/meteor-meteor/src/main/java/eu/stratosphere/meteor/Meteor.g:189:57: end= UINT
                            {
                            end=(Token)match(input,UINT,FOLLOW_UINT_in_arrayAccess1252); if (state.failed) return retval; 
                            if ( state.backtracking==0 ) stream_UINT.add(end);


                            }
                            break;

                    }


                    char_literal54=(Token)match(input,59,FOLLOW_59_in_arrayAccess1255); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_59.add(char_literal54);


                    // AST REWRITE
                    // elements: 
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    if ( state.backtracking==0 ) {

                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (EvaluationExpression)adaptor.nil();
                    // 190:3: -> ^( EXPRESSION[\"ArrayAccess\"] )
                    {
                        // /home/arv/workspace/master/meteor/meteor-meteor/src/main/java/eu/stratosphere/meteor/Meteor.g:190:6: ^( EXPRESSION[\"ArrayAccess\"] )
                        {
                        EvaluationExpression root_1 = (EvaluationExpression)adaptor.nil();
                        root_1 = (EvaluationExpression)adaptor.becomeRoot(
                        (EvaluationExpression)adaptor.create(EXPRESSION, "ArrayAccess")
                        , root_1);

                        adaptor.addChild(root_1,  Integer.valueOf((start!=null?start.getText():null)) );

                        adaptor.addChild(root_1,  Integer.valueOf((end!=null?end.getText():null)) );

                        adaptor.addChild(root_0, root_1);
                        }

                    }


                    retval.tree = root_0;
                    }

                    }
                    break;

            }
            retval.stop = input.LT(-1);


            if ( state.backtracking==0 ) {

            retval.tree = (EvaluationExpression)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }

        catch (RecognitionException e) {
          throw e;
        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "arrayAccess"


    public static class valueExpression_return extends ParserRuleReturnScope {
        EvaluationExpression tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "valueExpression"
    // /home/arv/workspace/master/meteor/meteor-meteor/src/main/java/eu/stratosphere/meteor/Meteor.g:192:1: valueExpression : ( ( ID '(' )=> methodCall[null] | parenthesesExpression | literal | ( VAR '[' VAR )=> streamIndexAccess | VAR ->| ( ( ID ':' )=>packageName= ID ':' )? constant= ID {...}? => ->| arrayCreation | objectCreation );
    public final MeteorParser.valueExpression_return valueExpression() throws RecognitionException {
        MeteorParser.valueExpression_return retval = new MeteorParser.valueExpression_return();
        retval.start = input.LT(1);


        EvaluationExpression root_0 = null;

        Token packageName=null;
        Token constant=null;
        Token VAR59=null;
        Token char_literal60=null;
        MeteorParser.methodCall_return methodCall55 =null;

        MeteorParser.parenthesesExpression_return parenthesesExpression56 =null;

        MeteorParser.literal_return literal57 =null;

        MeteorParser.streamIndexAccess_return streamIndexAccess58 =null;

        MeteorParser.arrayCreation_return arrayCreation61 =null;

        MeteorParser.objectCreation_return objectCreation62 =null;


        EvaluationExpression packageName_tree=null;
        EvaluationExpression constant_tree=null;
        EvaluationExpression VAR59_tree=null;
        EvaluationExpression char_literal60_tree=null;
        RewriteRuleTokenStream stream_48=new RewriteRuleTokenStream(adaptor,"token 48");
        RewriteRuleTokenStream stream_VAR=new RewriteRuleTokenStream(adaptor,"token VAR");
        RewriteRuleTokenStream stream_ID=new RewriteRuleTokenStream(adaptor,"token ID");

        try {
            // /home/arv/workspace/master/meteor/meteor-meteor/src/main/java/eu/stratosphere/meteor/Meteor.g:193:2: ( ( ID '(' )=> methodCall[null] | parenthesesExpression | literal | ( VAR '[' VAR )=> streamIndexAccess | VAR ->| ( ( ID ':' )=>packageName= ID ':' )? constant= ID {...}? => ->| arrayCreation | objectCreation )
            int alt31=8;
            switch ( input.LA(1) ) {
            case ID:
                {
                int LA31_1 = input.LA(2);

                if ( (LA31_1==48) ) {
                    int LA31_7 = input.LA(3);

                    if ( (synpred11_Meteor()) ) {
                        alt31=1;
                    }
                    else if ( (true) ) {
                        alt31=6;
                    }
                    else {
                        if (state.backtracking>0) {state.failed=true; return retval;}
                        NoViableAltException nvae =
                            new NoViableAltException("", 31, 7, input);

                        throw nvae;

                    }
                }
                else if ( (LA31_1==39) && (synpred11_Meteor())) {
                    alt31=1;
                }
                else if ( (LA31_1==EOF||LA31_1==AND||LA31_1==AS||(LA31_1 >= ID && LA31_1 <= IN)||LA31_1==NOT||LA31_1==OR||LA31_1==STAR||(LA31_1 >= 36 && LA31_1 <= 37)||(LA31_1 >= 40 && LA31_1 <= 41)||(LA31_1 >= 43 && LA31_1 <= 44)||(LA31_1 >= 46 && LA31_1 <= 47)||(LA31_1 >= 49 && LA31_1 <= 51)||(LA31_1 >= 53 && LA31_1 <= 59)||(LA31_1 >= 69 && LA31_1 <= 70)) ) {
                    alt31=6;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return retval;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 31, 1, input);

                    throw nvae;

                }
                }
                break;
            case 39:
                {
                alt31=2;
                }
                break;
            case DECIMAL:
            case INTEGER:
            case STRING:
            case UINT:
            case 60:
            case 62:
            case 65:
                {
                alt31=3;
                }
                break;
            case VAR:
                {
                int LA31_4 = input.LA(2);

                if ( (LA31_4==58) ) {
                    int LA31_10 = input.LA(3);

                    if ( (LA31_10==STAR) ) {
                        alt31=5;
                    }
                    else if ( (LA31_10==ID) && (synpred12_Meteor())) {
                        alt31=4;
                    }
                    else if ( (LA31_10==39) && (synpred12_Meteor())) {
                        alt31=4;
                    }
                    else if ( (LA31_10==65) && (synpred12_Meteor())) {
                        alt31=4;
                    }
                    else if ( (LA31_10==60) && (synpred12_Meteor())) {
                        alt31=4;
                    }
                    else if ( (LA31_10==DECIMAL) && (synpred12_Meteor())) {
                        alt31=4;
                    }
                    else if ( (LA31_10==STRING) && (synpred12_Meteor())) {
                        alt31=4;
                    }
                    else if ( (LA31_10==UINT) ) {
                        int LA31_18 = input.LA(4);

                        if ( (LA31_18==57) && (synpred12_Meteor())) {
                            alt31=4;
                        }
                        else if ( (LA31_18==46) && (synpred12_Meteor())) {
                            alt31=4;
                        }
                        else if ( (LA31_18==58) && (synpred12_Meteor())) {
                            alt31=4;
                        }
                        else if ( (LA31_18==59) ) {
                            int LA31_27 = input.LA(5);

                            if ( (synpred12_Meteor()) ) {
                                alt31=4;
                            }
                            else if ( (true) ) {
                                alt31=5;
                            }
                            else {
                                if (state.backtracking>0) {state.failed=true; return retval;}
                                NoViableAltException nvae =
                                    new NoViableAltException("", 31, 27, input);

                                throw nvae;

                            }
                        }
                        else if ( (LA31_18==48) ) {
                            alt31=5;
                        }
                        else {
                            if (state.backtracking>0) {state.failed=true; return retval;}
                            NoViableAltException nvae =
                                new NoViableAltException("", 31, 18, input);

                            throw nvae;

                        }
                    }
                    else if ( (LA31_10==INTEGER) ) {
                        int LA31_19 = input.LA(4);

                        if ( (LA31_19==57) && (synpred12_Meteor())) {
                            alt31=4;
                        }
                        else if ( (LA31_19==46) && (synpred12_Meteor())) {
                            alt31=4;
                        }
                        else if ( (LA31_19==58) && (synpred12_Meteor())) {
                            alt31=4;
                        }
                        else if ( (LA31_19==59) ) {
                            int LA31_28 = input.LA(5);

                            if ( (synpred12_Meteor()) ) {
                                alt31=4;
                            }
                            else if ( (true) ) {
                                alt31=5;
                            }
                            else {
                                if (state.backtracking>0) {state.failed=true; return retval;}
                                NoViableAltException nvae =
                                    new NoViableAltException("", 31, 28, input);

                                throw nvae;

                            }
                        }
                        else if ( (LA31_19==48) ) {
                            alt31=5;
                        }
                        else {
                            if (state.backtracking>0) {state.failed=true; return retval;}
                            NoViableAltException nvae =
                                new NoViableAltException("", 31, 19, input);

                            throw nvae;

                        }
                    }
                    else if ( (LA31_10==62) && (synpred12_Meteor())) {
                        alt31=4;
                    }
                    else if ( (LA31_10==VAR) && (synpred12_Meteor())) {
                        alt31=4;
                    }
                    else if ( (LA31_10==58) && (synpred12_Meteor())) {
                        alt31=4;
                    }
                    else if ( (LA31_10==68) && (synpred12_Meteor())) {
                        alt31=4;
                    }
                    else {
                        if (state.backtracking>0) {state.failed=true; return retval;}
                        NoViableAltException nvae =
                            new NoViableAltException("", 31, 10, input);

                        throw nvae;

                    }
                }
                else if ( (LA31_4==EOF||LA31_4==AND||LA31_4==AS||(LA31_4 >= ID && LA31_4 <= IN)||LA31_4==NOT||LA31_4==OR||LA31_4==STAR||(LA31_4 >= 36 && LA31_4 <= 37)||(LA31_4 >= 40 && LA31_4 <= 41)||(LA31_4 >= 43 && LA31_4 <= 44)||(LA31_4 >= 46 && LA31_4 <= 51)||(LA31_4 >= 53 && LA31_4 <= 57)||LA31_4==59||(LA31_4 >= 69 && LA31_4 <= 70)) ) {
                    alt31=5;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return retval;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 31, 4, input);

                    throw nvae;

                }
                }
                break;
            case 58:
                {
                alt31=7;
                }
                break;
            case 68:
                {
                alt31=8;
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("", 31, 0, input);

                throw nvae;

            }

            switch (alt31) {
                case 1 :
                    // /home/arv/workspace/master/meteor/meteor-meteor/src/main/java/eu/stratosphere/meteor/Meteor.g:193:4: ( ID '(' )=> methodCall[null]
                    {
                    root_0 = (EvaluationExpression)adaptor.nil();


                    pushFollow(FOLLOW_methodCall_in_valueExpression1287);
                    methodCall55=methodCall(null);

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, methodCall55.getTree());

                    }
                    break;
                case 2 :
                    // /home/arv/workspace/master/meteor/meteor-meteor/src/main/java/eu/stratosphere/meteor/Meteor.g:194:4: parenthesesExpression
                    {
                    root_0 = (EvaluationExpression)adaptor.nil();


                    pushFollow(FOLLOW_parenthesesExpression_in_valueExpression1293);
                    parenthesesExpression56=parenthesesExpression();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, parenthesesExpression56.getTree());

                    }
                    break;
                case 3 :
                    // /home/arv/workspace/master/meteor/meteor-meteor/src/main/java/eu/stratosphere/meteor/Meteor.g:195:4: literal
                    {
                    root_0 = (EvaluationExpression)adaptor.nil();


                    pushFollow(FOLLOW_literal_in_valueExpression1299);
                    literal57=literal();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, literal57.getTree());

                    }
                    break;
                case 4 :
                    // /home/arv/workspace/master/meteor/meteor-meteor/src/main/java/eu/stratosphere/meteor/Meteor.g:196:4: ( VAR '[' VAR )=> streamIndexAccess
                    {
                    root_0 = (EvaluationExpression)adaptor.nil();


                    pushFollow(FOLLOW_streamIndexAccess_in_valueExpression1314);
                    streamIndexAccess58=streamIndexAccess();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, streamIndexAccess58.getTree());

                    }
                    break;
                case 5 :
                    // /home/arv/workspace/master/meteor/meteor-meteor/src/main/java/eu/stratosphere/meteor/Meteor.g:197:4: VAR
                    {
                    VAR59=(Token)match(input,VAR,FOLLOW_VAR_in_valueExpression1319); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_VAR.add(VAR59);


                    // AST REWRITE
                    // elements: 
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    if ( state.backtracking==0 ) {

                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (EvaluationExpression)adaptor.nil();
                    // 197:8: ->
                    {
                        adaptor.addChild(root_0,  getInputSelection(VAR59) );

                    }


                    retval.tree = root_0;
                    }

                    }
                    break;
                case 6 :
                    // /home/arv/workspace/master/meteor/meteor-meteor/src/main/java/eu/stratosphere/meteor/Meteor.g:198:5: ( ( ID ':' )=>packageName= ID ':' )? constant= ID {...}? =>
                    {
                    // /home/arv/workspace/master/meteor/meteor-meteor/src/main/java/eu/stratosphere/meteor/Meteor.g:198:5: ( ( ID ':' )=>packageName= ID ':' )?
                    int alt30=2;
                    int LA30_0 = input.LA(1);

                    if ( (LA30_0==ID) ) {
                        int LA30_1 = input.LA(2);

                        if ( (LA30_1==48) ) {
                            int LA30_2 = input.LA(3);

                            if ( (synpred13_Meteor()) ) {
                                alt30=1;
                            }
                        }
                    }
                    switch (alt30) {
                        case 1 :
                            // /home/arv/workspace/master/meteor/meteor-meteor/src/main/java/eu/stratosphere/meteor/Meteor.g:198:6: ( ID ':' )=>packageName= ID ':'
                            {
                            packageName=(Token)match(input,ID,FOLLOW_ID_in_valueExpression1339); if (state.failed) return retval; 
                            if ( state.backtracking==0 ) stream_ID.add(packageName);


                            char_literal60=(Token)match(input,48,FOLLOW_48_in_valueExpression1341); if (state.failed) return retval; 
                            if ( state.backtracking==0 ) stream_48.add(char_literal60);


                            }
                            break;

                    }


                    constant=(Token)match(input,ID,FOLLOW_ID_in_valueExpression1347); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_ID.add(constant);


                    if ( !(( getScope((packageName!=null?packageName.getText():null)).getConstantRegistry().get((constant!=null?constant.getText():null)) != null )) ) {
                        if (state.backtracking>0) {state.failed=true; return retval;}
                        throw new FailedPredicateException(input, "valueExpression", " getScope($packageName.text).getConstantRegistry().get($constant.text) != null ");
                    }

                    // AST REWRITE
                    // elements: 
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    if ( state.backtracking==0 ) {

                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (EvaluationExpression)adaptor.nil();
                    // 199:5: ->
                    {
                        adaptor.addChild(root_0,  getScope((packageName!=null?packageName.getText():null)).getConstantRegistry().get((constant!=null?constant.getText():null)) );

                    }


                    retval.tree = root_0;
                    }

                    }
                    break;
                case 7 :
                    // /home/arv/workspace/master/meteor/meteor-meteor/src/main/java/eu/stratosphere/meteor/Meteor.g:200:4: arrayCreation
                    {
                    root_0 = (EvaluationExpression)adaptor.nil();


                    pushFollow(FOLLOW_arrayCreation_in_valueExpression1367);
                    arrayCreation61=arrayCreation();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, arrayCreation61.getTree());

                    }
                    break;
                case 8 :
                    // /home/arv/workspace/master/meteor/meteor-meteor/src/main/java/eu/stratosphere/meteor/Meteor.g:201:4: objectCreation
                    {
                    root_0 = (EvaluationExpression)adaptor.nil();


                    pushFollow(FOLLOW_objectCreation_in_valueExpression1373);
                    objectCreation62=objectCreation();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, objectCreation62.getTree());

                    }
                    break;

            }
            retval.stop = input.LT(-1);


            if ( state.backtracking==0 ) {

            retval.tree = (EvaluationExpression)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }

        catch (RecognitionException e) {
          throw e;
        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "valueExpression"


    public static class operatorExpression_return extends ParserRuleReturnScope {
        EvaluationExpression tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "operatorExpression"
    // /home/arv/workspace/master/meteor/meteor-meteor/src/main/java/eu/stratosphere/meteor/Meteor.g:203:1: operatorExpression : op= operator -> ^( EXPRESSION[\"NestedOperatorExpression\"] ) ;
    public final MeteorParser.operatorExpression_return operatorExpression() throws RecognitionException {
        MeteorParser.operatorExpression_return retval = new MeteorParser.operatorExpression_return();
        retval.start = input.LT(1);


        EvaluationExpression root_0 = null;

        MeteorParser.operator_return op =null;


        RewriteRuleSubtreeStream stream_operator=new RewriteRuleSubtreeStream(adaptor,"rule operator");
        try {
            // /home/arv/workspace/master/meteor/meteor-meteor/src/main/java/eu/stratosphere/meteor/Meteor.g:204:2: (op= operator -> ^( EXPRESSION[\"NestedOperatorExpression\"] ) )
            // /home/arv/workspace/master/meteor/meteor-meteor/src/main/java/eu/stratosphere/meteor/Meteor.g:204:4: op= operator
            {
            pushFollow(FOLLOW_operator_in_operatorExpression1386);
            op=operator();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) stream_operator.add(op.getTree());

            // AST REWRITE
            // elements: 
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            if ( state.backtracking==0 ) {

            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

            root_0 = (EvaluationExpression)adaptor.nil();
            // 204:16: -> ^( EXPRESSION[\"NestedOperatorExpression\"] )
            {
                // /home/arv/workspace/master/meteor/meteor-meteor/src/main/java/eu/stratosphere/meteor/Meteor.g:204:19: ^( EXPRESSION[\"NestedOperatorExpression\"] )
                {
                EvaluationExpression root_1 = (EvaluationExpression)adaptor.nil();
                root_1 = (EvaluationExpression)adaptor.becomeRoot(
                (EvaluationExpression)adaptor.create(EXPRESSION, "NestedOperatorExpression")
                , root_1);

                adaptor.addChild(root_1,  (op!=null?op.op:null) );

                adaptor.addChild(root_0, root_1);
                }

            }


            retval.tree = root_0;
            }

            }

            retval.stop = input.LT(-1);


            if ( state.backtracking==0 ) {

            retval.tree = (EvaluationExpression)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }

        catch (RecognitionException e) {
          throw e;
        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "operatorExpression"


    public static class parenthesesExpression_return extends ParserRuleReturnScope {
        EvaluationExpression tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "parenthesesExpression"
    // /home/arv/workspace/master/meteor/meteor-meteor/src/main/java/eu/stratosphere/meteor/Meteor.g:206:1: parenthesesExpression : ( '(' expression ')' ) -> expression ;
    public final MeteorParser.parenthesesExpression_return parenthesesExpression() throws RecognitionException {
        MeteorParser.parenthesesExpression_return retval = new MeteorParser.parenthesesExpression_return();
        retval.start = input.LT(1);


        EvaluationExpression root_0 = null;

        Token char_literal63=null;
        Token char_literal65=null;
        MeteorParser.expression_return expression64 =null;


        EvaluationExpression char_literal63_tree=null;
        EvaluationExpression char_literal65_tree=null;
        RewriteRuleTokenStream stream_40=new RewriteRuleTokenStream(adaptor,"token 40");
        RewriteRuleTokenStream stream_39=new RewriteRuleTokenStream(adaptor,"token 39");
        RewriteRuleSubtreeStream stream_expression=new RewriteRuleSubtreeStream(adaptor,"rule expression");
        try {
            // /home/arv/workspace/master/meteor/meteor-meteor/src/main/java/eu/stratosphere/meteor/Meteor.g:207:2: ( ( '(' expression ')' ) -> expression )
            // /home/arv/workspace/master/meteor/meteor-meteor/src/main/java/eu/stratosphere/meteor/Meteor.g:207:4: ( '(' expression ')' )
            {
            // /home/arv/workspace/master/meteor/meteor-meteor/src/main/java/eu/stratosphere/meteor/Meteor.g:207:4: ( '(' expression ')' )
            // /home/arv/workspace/master/meteor/meteor-meteor/src/main/java/eu/stratosphere/meteor/Meteor.g:207:5: '(' expression ')'
            {
            char_literal63=(Token)match(input,39,FOLLOW_39_in_parenthesesExpression1407); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_39.add(char_literal63);


            pushFollow(FOLLOW_expression_in_parenthesesExpression1409);
            expression64=expression();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) stream_expression.add(expression64.getTree());

            char_literal65=(Token)match(input,40,FOLLOW_40_in_parenthesesExpression1411); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_40.add(char_literal65);


            }


            // AST REWRITE
            // elements: expression
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            if ( state.backtracking==0 ) {

            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

            root_0 = (EvaluationExpression)adaptor.nil();
            // 207:25: -> expression
            {
                adaptor.addChild(root_0, stream_expression.nextTree());

            }


            retval.tree = root_0;
            }

            }

            retval.stop = input.LT(-1);


            if ( state.backtracking==0 ) {

            retval.tree = (EvaluationExpression)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }

        catch (RecognitionException e) {
          throw e;
        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "parenthesesExpression"


    public static class methodCall_return extends ParserRuleReturnScope {
        EvaluationExpression tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "methodCall"
    // /home/arv/workspace/master/meteor/meteor-meteor/src/main/java/eu/stratosphere/meteor/Meteor.g:209:1: methodCall[EvaluationExpression targetExpr] : (packageName= ID ':' )? name= ID '(' ( (param= expression |func= lowerOrderFunction ) ( ',' (param= expression |func= lowerOrderFunction ) )* )? ')' ->;
    public final MeteorParser.methodCall_return methodCall(EvaluationExpression targetExpr) throws RecognitionException {
        MeteorParser.methodCall_return retval = new MeteorParser.methodCall_return();
        retval.start = input.LT(1);


        EvaluationExpression root_0 = null;

        Token packageName=null;
        Token name=null;
        Token char_literal66=null;
        Token char_literal67=null;
        Token char_literal68=null;
        Token char_literal69=null;
        MeteorParser.expression_return param =null;

        MeteorParser.lowerOrderFunction_return func =null;


        EvaluationExpression packageName_tree=null;
        EvaluationExpression name_tree=null;
        EvaluationExpression char_literal66_tree=null;
        EvaluationExpression char_literal67_tree=null;
        EvaluationExpression char_literal68_tree=null;
        EvaluationExpression char_literal69_tree=null;
        RewriteRuleTokenStream stream_48=new RewriteRuleTokenStream(adaptor,"token 48");
        RewriteRuleTokenStream stream_43=new RewriteRuleTokenStream(adaptor,"token 43");
        RewriteRuleTokenStream stream_40=new RewriteRuleTokenStream(adaptor,"token 40");
        RewriteRuleTokenStream stream_ID=new RewriteRuleTokenStream(adaptor,"token ID");
        RewriteRuleTokenStream stream_39=new RewriteRuleTokenStream(adaptor,"token 39");
        RewriteRuleSubtreeStream stream_expression=new RewriteRuleSubtreeStream(adaptor,"rule expression");
        RewriteRuleSubtreeStream stream_lowerOrderFunction=new RewriteRuleSubtreeStream(adaptor,"rule lowerOrderFunction");
         List<EvaluationExpression> params = new ArrayList();
                paraphrase.push("a method call"); 
        try {
            // /home/arv/workspace/master/meteor/meteor-meteor/src/main/java/eu/stratosphere/meteor/Meteor.g:213:2: ( (packageName= ID ':' )? name= ID '(' ( (param= expression |func= lowerOrderFunction ) ( ',' (param= expression |func= lowerOrderFunction ) )* )? ')' ->)
            // /home/arv/workspace/master/meteor/meteor-meteor/src/main/java/eu/stratosphere/meteor/Meteor.g:213:4: (packageName= ID ':' )? name= ID '(' ( (param= expression |func= lowerOrderFunction ) ( ',' (param= expression |func= lowerOrderFunction ) )* )? ')'
            {
            // /home/arv/workspace/master/meteor/meteor-meteor/src/main/java/eu/stratosphere/meteor/Meteor.g:213:4: (packageName= ID ':' )?
            int alt32=2;
            int LA32_0 = input.LA(1);

            if ( (LA32_0==ID) ) {
                int LA32_1 = input.LA(2);

                if ( (LA32_1==48) ) {
                    alt32=1;
                }
            }
            switch (alt32) {
                case 1 :
                    // /home/arv/workspace/master/meteor/meteor-meteor/src/main/java/eu/stratosphere/meteor/Meteor.g:213:5: packageName= ID ':'
                    {
                    packageName=(Token)match(input,ID,FOLLOW_ID_in_methodCall1440); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_ID.add(packageName);


                    char_literal66=(Token)match(input,48,FOLLOW_48_in_methodCall1442); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_48.add(char_literal66);


                    }
                    break;

            }


            name=(Token)match(input,ID,FOLLOW_ID_in_methodCall1448); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_ID.add(name);


            char_literal67=(Token)match(input,39,FOLLOW_39_in_methodCall1450); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_39.add(char_literal67);


            // /home/arv/workspace/master/meteor/meteor-meteor/src/main/java/eu/stratosphere/meteor/Meteor.g:214:2: ( (param= expression |func= lowerOrderFunction ) ( ',' (param= expression |func= lowerOrderFunction ) )* )?
            int alt36=2;
            int LA36_0 = input.LA(1);

            if ( (LA36_0==DECIMAL||LA36_0==FN||LA36_0==ID||LA36_0==INTEGER||(LA36_0 >= STRING && LA36_0 <= UINT)||LA36_0==VAR||LA36_0==35||(LA36_0 >= 38 && LA36_0 <= 39)||LA36_0==42||LA36_0==45||LA36_0==58||LA36_0==60||(LA36_0 >= 62 && LA36_0 <= 63)||LA36_0==65||(LA36_0 >= 67 && LA36_0 <= 68)||LA36_0==71) ) {
                alt36=1;
            }
            switch (alt36) {
                case 1 :
                    // /home/arv/workspace/master/meteor/meteor-meteor/src/main/java/eu/stratosphere/meteor/Meteor.g:214:3: (param= expression |func= lowerOrderFunction ) ( ',' (param= expression |func= lowerOrderFunction ) )*
                    {
                    // /home/arv/workspace/master/meteor/meteor-meteor/src/main/java/eu/stratosphere/meteor/Meteor.g:214:3: (param= expression |func= lowerOrderFunction )
                    int alt33=2;
                    int LA33_0 = input.LA(1);

                    if ( (LA33_0==DECIMAL||LA33_0==ID||LA33_0==INTEGER||(LA33_0 >= STRING && LA33_0 <= UINT)||LA33_0==VAR||LA33_0==35||LA33_0==39||LA33_0==42||LA33_0==45||LA33_0==58||LA33_0==60||(LA33_0 >= 62 && LA33_0 <= 63)||LA33_0==65||(LA33_0 >= 67 && LA33_0 <= 68)||LA33_0==71) ) {
                        alt33=1;
                    }
                    else if ( (LA33_0==FN||LA33_0==38) ) {
                        alt33=2;
                    }
                    else {
                        if (state.backtracking>0) {state.failed=true; return retval;}
                        NoViableAltException nvae =
                            new NoViableAltException("", 33, 0, input);

                        throw nvae;

                    }
                    switch (alt33) {
                        case 1 :
                            // /home/arv/workspace/master/meteor/meteor-meteor/src/main/java/eu/stratosphere/meteor/Meteor.g:214:4: param= expression
                            {
                            pushFollow(FOLLOW_expression_in_methodCall1458);
                            param=expression();

                            state._fsp--;
                            if (state.failed) return retval;
                            if ( state.backtracking==0 ) stream_expression.add(param.getTree());

                            if ( state.backtracking==0 ) { params.add((param!=null?((EvaluationExpression)param.tree):null)); }

                            }
                            break;
                        case 2 :
                            // /home/arv/workspace/master/meteor/meteor-meteor/src/main/java/eu/stratosphere/meteor/Meteor.g:214:52: func= lowerOrderFunction
                            {
                            pushFollow(FOLLOW_lowerOrderFunction_in_methodCall1466);
                            func=lowerOrderFunction();

                            state._fsp--;
                            if (state.failed) return retval;
                            if ( state.backtracking==0 ) stream_lowerOrderFunction.add(func.getTree());

                            if ( state.backtracking==0 ) { params.add((func!=null?((EvaluationExpression)func.tree):null)); }

                            }
                            break;

                    }


                    // /home/arv/workspace/master/meteor/meteor-meteor/src/main/java/eu/stratosphere/meteor/Meteor.g:215:2: ( ',' (param= expression |func= lowerOrderFunction ) )*
                    loop35:
                    do {
                        int alt35=2;
                        int LA35_0 = input.LA(1);

                        if ( (LA35_0==43) ) {
                            alt35=1;
                        }


                        switch (alt35) {
                    	case 1 :
                    	    // /home/arv/workspace/master/meteor/meteor-meteor/src/main/java/eu/stratosphere/meteor/Meteor.g:215:3: ',' (param= expression |func= lowerOrderFunction )
                    	    {
                    	    char_literal68=(Token)match(input,43,FOLLOW_43_in_methodCall1474); if (state.failed) return retval; 
                    	    if ( state.backtracking==0 ) stream_43.add(char_literal68);


                    	    // /home/arv/workspace/master/meteor/meteor-meteor/src/main/java/eu/stratosphere/meteor/Meteor.g:215:7: (param= expression |func= lowerOrderFunction )
                    	    int alt34=2;
                    	    int LA34_0 = input.LA(1);

                    	    if ( (LA34_0==DECIMAL||LA34_0==ID||LA34_0==INTEGER||(LA34_0 >= STRING && LA34_0 <= UINT)||LA34_0==VAR||LA34_0==35||LA34_0==39||LA34_0==42||LA34_0==45||LA34_0==58||LA34_0==60||(LA34_0 >= 62 && LA34_0 <= 63)||LA34_0==65||(LA34_0 >= 67 && LA34_0 <= 68)||LA34_0==71) ) {
                    	        alt34=1;
                    	    }
                    	    else if ( (LA34_0==FN||LA34_0==38) ) {
                    	        alt34=2;
                    	    }
                    	    else {
                    	        if (state.backtracking>0) {state.failed=true; return retval;}
                    	        NoViableAltException nvae =
                    	            new NoViableAltException("", 34, 0, input);

                    	        throw nvae;

                    	    }
                    	    switch (alt34) {
                    	        case 1 :
                    	            // /home/arv/workspace/master/meteor/meteor-meteor/src/main/java/eu/stratosphere/meteor/Meteor.g:215:8: param= expression
                    	            {
                    	            pushFollow(FOLLOW_expression_in_methodCall1479);
                    	            param=expression();

                    	            state._fsp--;
                    	            if (state.failed) return retval;
                    	            if ( state.backtracking==0 ) stream_expression.add(param.getTree());

                    	            if ( state.backtracking==0 ) { params.add((param!=null?((EvaluationExpression)param.tree):null)); }

                    	            }
                    	            break;
                    	        case 2 :
                    	            // /home/arv/workspace/master/meteor/meteor-meteor/src/main/java/eu/stratosphere/meteor/Meteor.g:215:56: func= lowerOrderFunction
                    	            {
                    	            pushFollow(FOLLOW_lowerOrderFunction_in_methodCall1487);
                    	            func=lowerOrderFunction();

                    	            state._fsp--;
                    	            if (state.failed) return retval;
                    	            if ( state.backtracking==0 ) stream_lowerOrderFunction.add(func.getTree());

                    	            if ( state.backtracking==0 ) { params.add((func!=null?((EvaluationExpression)func.tree):null)); }

                    	            }
                    	            break;

                    	    }


                    	    }
                    	    break;

                    	default :
                    	    break loop35;
                        }
                    } while (true);


                    }
                    break;

            }


            char_literal69=(Token)match(input,40,FOLLOW_40_in_methodCall1498); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_40.add(char_literal69);


            // AST REWRITE
            // elements: 
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            if ( state.backtracking==0 ) {

            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

            root_0 = (EvaluationExpression)adaptor.nil();
            // 216:6: ->
            {
                adaptor.addChild(root_0,  createCheckedMethodCall((packageName!=null?packageName.getText():null), name, targetExpr, params.toArray(new EvaluationExpression[params.size()])) );

            }


            retval.tree = root_0;
            }

            }

            retval.stop = input.LT(-1);


            if ( state.backtracking==0 ) {

            retval.tree = (EvaluationExpression)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
            if ( state.backtracking==0 ) { paraphrase.pop(); }
        }

        catch (RecognitionException e) {
          throw e;
        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "methodCall"


    public static class lowerOrderFunction_return extends ParserRuleReturnScope {
        EvaluationExpression tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "lowerOrderFunction"
    // /home/arv/workspace/master/meteor/meteor-meteor/src/main/java/eu/stratosphere/meteor/Meteor.g:218:1: lowerOrderFunction : ( '&' (packageName= ID ':' )? name= ID -> ^( EXPRESSION[\"ConstantExpression\"] ) |func= inlineFunction -> ^( EXPRESSION[\"ConstantExpression\"] ) );
    public final MeteorParser.lowerOrderFunction_return lowerOrderFunction() throws RecognitionException {
        MeteorParser.lowerOrderFunction_return retval = new MeteorParser.lowerOrderFunction_return();
        retval.start = input.LT(1);


        EvaluationExpression root_0 = null;

        Token packageName=null;
        Token name=null;
        Token char_literal70=null;
        Token char_literal71=null;
        MeteorParser.inlineFunction_return func =null;


        EvaluationExpression packageName_tree=null;
        EvaluationExpression name_tree=null;
        EvaluationExpression char_literal70_tree=null;
        EvaluationExpression char_literal71_tree=null;
        RewriteRuleTokenStream stream_48=new RewriteRuleTokenStream(adaptor,"token 48");
        RewriteRuleTokenStream stream_ID=new RewriteRuleTokenStream(adaptor,"token ID");
        RewriteRuleTokenStream stream_38=new RewriteRuleTokenStream(adaptor,"token 38");
        RewriteRuleSubtreeStream stream_inlineFunction=new RewriteRuleSubtreeStream(adaptor,"rule inlineFunction");
        try {
            // /home/arv/workspace/master/meteor/meteor-meteor/src/main/java/eu/stratosphere/meteor/Meteor.g:219:3: ( '&' (packageName= ID ':' )? name= ID -> ^( EXPRESSION[\"ConstantExpression\"] ) |func= inlineFunction -> ^( EXPRESSION[\"ConstantExpression\"] ) )
            int alt38=2;
            int LA38_0 = input.LA(1);

            if ( (LA38_0==38) ) {
                alt38=1;
            }
            else if ( (LA38_0==FN) ) {
                alt38=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("", 38, 0, input);

                throw nvae;

            }
            switch (alt38) {
                case 1 :
                    // /home/arv/workspace/master/meteor/meteor-meteor/src/main/java/eu/stratosphere/meteor/Meteor.g:219:5: '&' (packageName= ID ':' )? name= ID
                    {
                    char_literal70=(Token)match(input,38,FOLLOW_38_in_lowerOrderFunction1513); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_38.add(char_literal70);


                    // /home/arv/workspace/master/meteor/meteor-meteor/src/main/java/eu/stratosphere/meteor/Meteor.g:219:9: (packageName= ID ':' )?
                    int alt37=2;
                    int LA37_0 = input.LA(1);

                    if ( (LA37_0==ID) ) {
                        int LA37_1 = input.LA(2);

                        if ( (LA37_1==48) ) {
                            alt37=1;
                        }
                    }
                    switch (alt37) {
                        case 1 :
                            // /home/arv/workspace/master/meteor/meteor-meteor/src/main/java/eu/stratosphere/meteor/Meteor.g:219:10: packageName= ID ':'
                            {
                            packageName=(Token)match(input,ID,FOLLOW_ID_in_lowerOrderFunction1518); if (state.failed) return retval; 
                            if ( state.backtracking==0 ) stream_ID.add(packageName);


                            char_literal71=(Token)match(input,48,FOLLOW_48_in_lowerOrderFunction1520); if (state.failed) return retval; 
                            if ( state.backtracking==0 ) stream_48.add(char_literal71);


                            }
                            break;

                    }


                    name=(Token)match(input,ID,FOLLOW_ID_in_lowerOrderFunction1526); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_ID.add(name);


                    // AST REWRITE
                    // elements: 
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    if ( state.backtracking==0 ) {

                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (EvaluationExpression)adaptor.nil();
                    // 220:9: -> ^( EXPRESSION[\"ConstantExpression\"] )
                    {
                        // /home/arv/workspace/master/meteor/meteor-meteor/src/main/java/eu/stratosphere/meteor/Meteor.g:220:12: ^( EXPRESSION[\"ConstantExpression\"] )
                        {
                        EvaluationExpression root_1 = (EvaluationExpression)adaptor.nil();
                        root_1 = (EvaluationExpression)adaptor.becomeRoot(
                        (EvaluationExpression)adaptor.create(EXPRESSION, "ConstantExpression")
                        , root_1);

                        adaptor.addChild(root_1,  new FunctionNode(getSopremoFunction((packageName!=null?packageName.getText():null), name)) );

                        adaptor.addChild(root_0, root_1);
                        }

                    }


                    retval.tree = root_0;
                    }

                    }
                    break;
                case 2 :
                    // /home/arv/workspace/master/meteor/meteor-meteor/src/main/java/eu/stratosphere/meteor/Meteor.g:221:5: func= inlineFunction
                    {
                    pushFollow(FOLLOW_inlineFunction_in_lowerOrderFunction1552);
                    func=inlineFunction();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_inlineFunction.add(func.getTree());

                    // AST REWRITE
                    // elements: 
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    if ( state.backtracking==0 ) {

                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (EvaluationExpression)adaptor.nil();
                    // 221:25: -> ^( EXPRESSION[\"ConstantExpression\"] )
                    {
                        // /home/arv/workspace/master/meteor/meteor-meteor/src/main/java/eu/stratosphere/meteor/Meteor.g:221:28: ^( EXPRESSION[\"ConstantExpression\"] )
                        {
                        EvaluationExpression root_1 = (EvaluationExpression)adaptor.nil();
                        root_1 = (EvaluationExpression)adaptor.becomeRoot(
                        (EvaluationExpression)adaptor.create(EXPRESSION, "ConstantExpression")
                        , root_1);

                        adaptor.addChild(root_1,  new FunctionNode((func!=null?func.func:null)) );

                        adaptor.addChild(root_0, root_1);
                        }

                    }


                    retval.tree = root_0;
                    }

                    }
                    break;

            }
            retval.stop = input.LT(-1);


            if ( state.backtracking==0 ) {

            retval.tree = (EvaluationExpression)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }

        catch (RecognitionException e) {
          throw e;
        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "lowerOrderFunction"


    public static class fieldAssignment_return extends ParserRuleReturnScope {
        EvaluationExpression tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "fieldAssignment"
    // /home/arv/workspace/master/meteor/meteor-meteor/src/main/java/eu/stratosphere/meteor/Meteor.g:223:1: fieldAssignment : ( ( ( ID ':' )=> ID ':' expression ->) | VAR ( '.' STAR ->| '=' op= operator {...}? =>|p= contextAwarePathExpression[getVariable($VAR).toInputSelection($operator::result)] ( ':' e2= expression ->| ->) ) );
    public final MeteorParser.fieldAssignment_return fieldAssignment() throws RecognitionException {
        MeteorParser.fieldAssignment_return retval = new MeteorParser.fieldAssignment_return();
        retval.start = input.LT(1);


        EvaluationExpression root_0 = null;

        Token ID72=null;
        Token char_literal73=null;
        Token VAR75=null;
        Token char_literal76=null;
        Token STAR77=null;
        Token char_literal78=null;
        Token char_literal79=null;
        MeteorParser.operator_return op =null;

        MeteorParser.contextAwarePathExpression_return p =null;

        MeteorParser.expression_return e2 =null;

        MeteorParser.expression_return expression74 =null;


        EvaluationExpression ID72_tree=null;
        EvaluationExpression char_literal73_tree=null;
        EvaluationExpression VAR75_tree=null;
        EvaluationExpression char_literal76_tree=null;
        EvaluationExpression STAR77_tree=null;
        EvaluationExpression char_literal78_tree=null;
        EvaluationExpression char_literal79_tree=null;
        RewriteRuleTokenStream stream_48=new RewriteRuleTokenStream(adaptor,"token 48");
        RewriteRuleTokenStream stream_VAR=new RewriteRuleTokenStream(adaptor,"token VAR");
        RewriteRuleTokenStream stream_STAR=new RewriteRuleTokenStream(adaptor,"token STAR");
        RewriteRuleTokenStream stream_46=new RewriteRuleTokenStream(adaptor,"token 46");
        RewriteRuleTokenStream stream_ID=new RewriteRuleTokenStream(adaptor,"token ID");
        RewriteRuleTokenStream stream_52=new RewriteRuleTokenStream(adaptor,"token 52");
        RewriteRuleSubtreeStream stream_expression=new RewriteRuleSubtreeStream(adaptor,"rule expression");
        RewriteRuleSubtreeStream stream_contextAwarePathExpression=new RewriteRuleSubtreeStream(adaptor,"rule contextAwarePathExpression");
        RewriteRuleSubtreeStream stream_operator=new RewriteRuleSubtreeStream(adaptor,"rule operator");
        try {
            // /home/arv/workspace/master/meteor/meteor-meteor/src/main/java/eu/stratosphere/meteor/Meteor.g:224:2: ( ( ( ID ':' )=> ID ':' expression ->) | VAR ( '.' STAR ->| '=' op= operator {...}? =>|p= contextAwarePathExpression[getVariable($VAR).toInputSelection($operator::result)] ( ':' e2= expression ->| ->) ) )
            int alt41=2;
            int LA41_0 = input.LA(1);

            if ( (LA41_0==ID) && (synpred14_Meteor())) {
                alt41=1;
            }
            else if ( (LA41_0==VAR) ) {
                alt41=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("", 41, 0, input);

                throw nvae;

            }
            switch (alt41) {
                case 1 :
                    // /home/arv/workspace/master/meteor/meteor-meteor/src/main/java/eu/stratosphere/meteor/Meteor.g:224:4: ( ( ID ':' )=> ID ':' expression ->)
                    {
                    // /home/arv/workspace/master/meteor/meteor-meteor/src/main/java/eu/stratosphere/meteor/Meteor.g:224:4: ( ( ID ':' )=> ID ':' expression ->)
                    // /home/arv/workspace/master/meteor/meteor-meteor/src/main/java/eu/stratosphere/meteor/Meteor.g:224:5: ( ID ':' )=> ID ':' expression
                    {
                    ID72=(Token)match(input,ID,FOLLOW_ID_in_fieldAssignment1578); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_ID.add(ID72);


                    char_literal73=(Token)match(input,48,FOLLOW_48_in_fieldAssignment1580); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_48.add(char_literal73);


                    pushFollow(FOLLOW_expression_in_fieldAssignment1582);
                    expression74=expression();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_expression.add(expression74.getTree());

                    if ( state.backtracking==0 ) { ((objectCreation_scope)objectCreation_stack.peek()).mappings.add(new ObjectCreation.FieldAssignment((ID72!=null?ID72.getText():null), (expression74!=null?((EvaluationExpression)expression74.tree):null))); }

                    // AST REWRITE
                    // elements: 
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    if ( state.backtracking==0 ) {

                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (EvaluationExpression)adaptor.nil();
                    // 225:104: ->
                    {
                        root_0 = null;
                    }


                    retval.tree = root_0;
                    }

                    }


                    }
                    break;
                case 2 :
                    // /home/arv/workspace/master/meteor/meteor-meteor/src/main/java/eu/stratosphere/meteor/Meteor.g:226:5: VAR ( '.' STAR ->| '=' op= operator {...}? =>|p= contextAwarePathExpression[getVariable($VAR).toInputSelection($operator::result)] ( ':' e2= expression ->| ->) )
                    {
                    VAR75=(Token)match(input,VAR,FOLLOW_VAR_in_fieldAssignment1599); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_VAR.add(VAR75);


                    // /home/arv/workspace/master/meteor/meteor-meteor/src/main/java/eu/stratosphere/meteor/Meteor.g:227:5: ( '.' STAR ->| '=' op= operator {...}? =>|p= contextAwarePathExpression[getVariable($VAR).toInputSelection($operator::result)] ( ':' e2= expression ->| ->) )
                    int alt40=3;
                    switch ( input.LA(1) ) {
                    case 46:
                        {
                        int LA40_1 = input.LA(2);

                        if ( (LA40_1==STAR) ) {
                            alt40=1;
                        }
                        else if ( (LA40_1==ID) ) {
                            alt40=3;
                        }
                        else {
                            if (state.backtracking>0) {state.failed=true; return retval;}
                            NoViableAltException nvae =
                                new NoViableAltException("", 40, 1, input);

                            throw nvae;

                        }
                        }
                        break;
                    case 52:
                        {
                        alt40=2;
                        }
                        break;
                    case 57:
                    case 58:
                        {
                        alt40=3;
                        }
                        break;
                    default:
                        if (state.backtracking>0) {state.failed=true; return retval;}
                        NoViableAltException nvae =
                            new NoViableAltException("", 40, 0, input);

                        throw nvae;

                    }

                    switch (alt40) {
                        case 1 :
                            // /home/arv/workspace/master/meteor/meteor-meteor/src/main/java/eu/stratosphere/meteor/Meteor.g:227:7: '.' STAR
                            {
                            char_literal76=(Token)match(input,46,FOLLOW_46_in_fieldAssignment1608); if (state.failed) return retval; 
                            if ( state.backtracking==0 ) stream_46.add(char_literal76);


                            STAR77=(Token)match(input,STAR,FOLLOW_STAR_in_fieldAssignment1610); if (state.failed) return retval; 
                            if ( state.backtracking==0 ) stream_STAR.add(STAR77);


                            if ( state.backtracking==0 ) { ((objectCreation_scope)objectCreation_stack.peek()).mappings.add(new ObjectCreation.CopyFields(getInputSelection(VAR75))); }

                            // AST REWRITE
                            // elements: 
                            // token labels: 
                            // rule labels: retval
                            // token list labels: 
                            // rule list labels: 
                            // wildcard labels: 
                            if ( state.backtracking==0 ) {

                            retval.tree = root_0;
                            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                            root_0 = (EvaluationExpression)adaptor.nil();
                            // 227:107: ->
                            {
                                root_0 = null;
                            }


                            retval.tree = root_0;
                            }

                            }
                            break;
                        case 2 :
                            // /home/arv/workspace/master/meteor/meteor-meteor/src/main/java/eu/stratosphere/meteor/Meteor.g:228:9: '=' op= operator {...}? =>
                            {
                            char_literal78=(Token)match(input,52,FOLLOW_52_in_fieldAssignment1624); if (state.failed) return retval; 
                            if ( state.backtracking==0 ) stream_52.add(char_literal78);


                            pushFollow(FOLLOW_operator_in_fieldAssignment1628);
                            op=operator();

                            state._fsp--;
                            if (state.failed) return retval;
                            if ( state.backtracking==0 ) stream_operator.add(op.getTree());

                            if ( !(( setInnerOutput(VAR75, (op!=null?op.op:null)) )) ) {
                                if (state.backtracking>0) {state.failed=true; return retval;}
                                throw new FailedPredicateException(input, "fieldAssignment", " setInnerOutput($VAR, $op.op) ");
                            }

                            }
                            break;
                        case 3 :
                            // /home/arv/workspace/master/meteor/meteor-meteor/src/main/java/eu/stratosphere/meteor/Meteor.g:229:9: p= contextAwarePathExpression[getVariable($VAR).toInputSelection($operator::result)] ( ':' e2= expression ->| ->)
                            {
                            pushFollow(FOLLOW_contextAwarePathExpression_in_fieldAssignment1643);
                            p=contextAwarePathExpression(getVariable(VAR75).toInputSelection(((operator_scope)operator_stack.peek()).result));

                            state._fsp--;
                            if (state.failed) return retval;
                            if ( state.backtracking==0 ) stream_contextAwarePathExpression.add(p.getTree());

                            // /home/arv/workspace/master/meteor/meteor-meteor/src/main/java/eu/stratosphere/meteor/Meteor.g:230:7: ( ':' e2= expression ->| ->)
                            int alt39=2;
                            int LA39_0 = input.LA(1);

                            if ( (LA39_0==48) ) {
                                alt39=1;
                            }
                            else if ( (LA39_0==43||LA39_0==70) ) {
                                alt39=2;
                            }
                            else {
                                if (state.backtracking>0) {state.failed=true; return retval;}
                                NoViableAltException nvae =
                                    new NoViableAltException("", 39, 0, input);

                                throw nvae;

                            }
                            switch (alt39) {
                                case 1 :
                                    // /home/arv/workspace/master/meteor/meteor-meteor/src/main/java/eu/stratosphere/meteor/Meteor.g:230:9: ':' e2= expression
                                    {
                                    char_literal79=(Token)match(input,48,FOLLOW_48_in_fieldAssignment1654); if (state.failed) return retval; 
                                    if ( state.backtracking==0 ) stream_48.add(char_literal79);


                                    pushFollow(FOLLOW_expression_in_fieldAssignment1658);
                                    e2=expression();

                                    state._fsp--;
                                    if (state.failed) return retval;
                                    if ( state.backtracking==0 ) stream_expression.add(e2.getTree());

                                    if ( state.backtracking==0 ) { ((objectCreation_scope)objectCreation_stack.peek()).mappings.add(new ObjectCreation.TagMapping((p!=null?((EvaluationExpression)p.tree):null), (e2!=null?((EvaluationExpression)e2.tree):null))); }

                                    // AST REWRITE
                                    // elements: 
                                    // token labels: 
                                    // rule labels: retval
                                    // token list labels: 
                                    // rule list labels: 
                                    // wildcard labels: 
                                    if ( state.backtracking==0 ) {

                                    retval.tree = root_0;
                                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                                    root_0 = (EvaluationExpression)adaptor.nil();
                                    // 230:112: ->
                                    {
                                        root_0 = null;
                                    }


                                    retval.tree = root_0;
                                    }

                                    }
                                    break;
                                case 2 :
                                    // /home/arv/workspace/master/meteor/meteor-meteor/src/main/java/eu/stratosphere/meteor/Meteor.g:231:23: 
                                    {
                                    if ( state.backtracking==0 ) { ((objectCreation_scope)objectCreation_stack.peek()).mappings.add(new ObjectCreation.FieldAssignment(getAssignmentName((p!=null?((EvaluationExpression)p.tree):null)), (p!=null?((EvaluationExpression)p.tree):null))); }

                                    // AST REWRITE
                                    // elements: 
                                    // token labels: 
                                    // rule labels: retval
                                    // token list labels: 
                                    // rule list labels: 
                                    // wildcard labels: 
                                    if ( state.backtracking==0 ) {

                                    retval.tree = root_0;
                                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                                    root_0 = (EvaluationExpression)adaptor.nil();
                                    // 231:131: ->
                                    {
                                        root_0 = null;
                                    }


                                    retval.tree = root_0;
                                    }

                                    }
                                    break;

                            }


                            }
                            break;

                    }


                    }
                    break;

            }
            retval.stop = input.LT(-1);


            if ( state.backtracking==0 ) {

            retval.tree = (EvaluationExpression)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (NoViableAltException re) {
             explainUsage("inside of a json object {...} only <field: expression>, <$var.path>, <$var = operator> or <$var: expression> are allowed", re); 
        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "fieldAssignment"


    protected static class objectCreation_scope {
        List<ObjectCreation.Mapping> mappings;
    }
    protected Stack objectCreation_stack = new Stack();


    public static class objectCreation_return extends ParserRuleReturnScope {
        EvaluationExpression tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "objectCreation"
    // /home/arv/workspace/master/meteor/meteor-meteor/src/main/java/eu/stratosphere/meteor/Meteor.g:236:1: objectCreation : '{' ( fieldAssignment ( ',' fieldAssignment )* ( ',' )? )? '}' -> ^( EXPRESSION[\"ObjectCreation\"] ) ;
    public final MeteorParser.objectCreation_return objectCreation() throws RecognitionException {
        objectCreation_stack.push(new objectCreation_scope());
        MeteorParser.objectCreation_return retval = new MeteorParser.objectCreation_return();
        retval.start = input.LT(1);


        EvaluationExpression root_0 = null;

        Token char_literal80=null;
        Token char_literal82=null;
        Token char_literal84=null;
        Token char_literal85=null;
        MeteorParser.fieldAssignment_return fieldAssignment81 =null;

        MeteorParser.fieldAssignment_return fieldAssignment83 =null;


        EvaluationExpression char_literal80_tree=null;
        EvaluationExpression char_literal82_tree=null;
        EvaluationExpression char_literal84_tree=null;
        EvaluationExpression char_literal85_tree=null;
        RewriteRuleTokenStream stream_68=new RewriteRuleTokenStream(adaptor,"token 68");
        RewriteRuleTokenStream stream_43=new RewriteRuleTokenStream(adaptor,"token 43");
        RewriteRuleTokenStream stream_70=new RewriteRuleTokenStream(adaptor,"token 70");
        RewriteRuleSubtreeStream stream_fieldAssignment=new RewriteRuleSubtreeStream(adaptor,"rule fieldAssignment");
         ((objectCreation_scope)objectCreation_stack.peek()).mappings = new ArrayList<ObjectCreation.Mapping>(); 
                paraphrase.push("a json object"); 
        try {
            // /home/arv/workspace/master/meteor/meteor-meteor/src/main/java/eu/stratosphere/meteor/Meteor.g:241:2: ( '{' ( fieldAssignment ( ',' fieldAssignment )* ( ',' )? )? '}' -> ^( EXPRESSION[\"ObjectCreation\"] ) )
            // /home/arv/workspace/master/meteor/meteor-meteor/src/main/java/eu/stratosphere/meteor/Meteor.g:241:4: '{' ( fieldAssignment ( ',' fieldAssignment )* ( ',' )? )? '}'
            {
            char_literal80=(Token)match(input,68,FOLLOW_68_in_objectCreation1723); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_68.add(char_literal80);


            // /home/arv/workspace/master/meteor/meteor-meteor/src/main/java/eu/stratosphere/meteor/Meteor.g:241:8: ( fieldAssignment ( ',' fieldAssignment )* ( ',' )? )?
            int alt44=2;
            int LA44_0 = input.LA(1);

            if ( (LA44_0==ID||LA44_0==VAR) ) {
                alt44=1;
            }
            switch (alt44) {
                case 1 :
                    // /home/arv/workspace/master/meteor/meteor-meteor/src/main/java/eu/stratosphere/meteor/Meteor.g:241:9: fieldAssignment ( ',' fieldAssignment )* ( ',' )?
                    {
                    pushFollow(FOLLOW_fieldAssignment_in_objectCreation1726);
                    fieldAssignment81=fieldAssignment();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_fieldAssignment.add(fieldAssignment81.getTree());

                    // /home/arv/workspace/master/meteor/meteor-meteor/src/main/java/eu/stratosphere/meteor/Meteor.g:241:25: ( ',' fieldAssignment )*
                    loop42:
                    do {
                        int alt42=2;
                        int LA42_0 = input.LA(1);

                        if ( (LA42_0==43) ) {
                            int LA42_1 = input.LA(2);

                            if ( (LA42_1==ID||LA42_1==VAR) ) {
                                alt42=1;
                            }


                        }


                        switch (alt42) {
                    	case 1 :
                    	    // /home/arv/workspace/master/meteor/meteor-meteor/src/main/java/eu/stratosphere/meteor/Meteor.g:241:26: ',' fieldAssignment
                    	    {
                    	    char_literal82=(Token)match(input,43,FOLLOW_43_in_objectCreation1729); if (state.failed) return retval; 
                    	    if ( state.backtracking==0 ) stream_43.add(char_literal82);


                    	    pushFollow(FOLLOW_fieldAssignment_in_objectCreation1731);
                    	    fieldAssignment83=fieldAssignment();

                    	    state._fsp--;
                    	    if (state.failed) return retval;
                    	    if ( state.backtracking==0 ) stream_fieldAssignment.add(fieldAssignment83.getTree());

                    	    }
                    	    break;

                    	default :
                    	    break loop42;
                        }
                    } while (true);


                    // /home/arv/workspace/master/meteor/meteor-meteor/src/main/java/eu/stratosphere/meteor/Meteor.g:241:48: ( ',' )?
                    int alt43=2;
                    int LA43_0 = input.LA(1);

                    if ( (LA43_0==43) ) {
                        alt43=1;
                    }
                    switch (alt43) {
                        case 1 :
                            // /home/arv/workspace/master/meteor/meteor-meteor/src/main/java/eu/stratosphere/meteor/Meteor.g:241:48: ','
                            {
                            char_literal84=(Token)match(input,43,FOLLOW_43_in_objectCreation1735); if (state.failed) return retval; 
                            if ( state.backtracking==0 ) stream_43.add(char_literal84);


                            }
                            break;

                    }


                    }
                    break;

            }


            char_literal85=(Token)match(input,70,FOLLOW_70_in_objectCreation1740); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_70.add(char_literal85);


            // AST REWRITE
            // elements: 
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            if ( state.backtracking==0 ) {

            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

            root_0 = (EvaluationExpression)adaptor.nil();
            // 241:59: -> ^( EXPRESSION[\"ObjectCreation\"] )
            {
                // /home/arv/workspace/master/meteor/meteor-meteor/src/main/java/eu/stratosphere/meteor/Meteor.g:241:62: ^( EXPRESSION[\"ObjectCreation\"] )
                {
                EvaluationExpression root_1 = (EvaluationExpression)adaptor.nil();
                root_1 = (EvaluationExpression)adaptor.becomeRoot(
                (EvaluationExpression)adaptor.create(EXPRESSION, "ObjectCreation")
                , root_1);

                adaptor.addChild(root_1,  ((objectCreation_scope)objectCreation_stack.peek()).mappings );

                adaptor.addChild(root_0, root_1);
                }

            }


            retval.tree = root_0;
            }

            }

            retval.stop = input.LT(-1);


            if ( state.backtracking==0 ) {

            retval.tree = (EvaluationExpression)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
            if ( state.backtracking==0 ) { paraphrase.pop(); }
        }
        catch (MissingTokenException re) {
             explainUsage("expected <,> or <}> after a complete field assignment inside of a json object", re); 
        }

        finally {
        	// do for sure before leaving
            objectCreation_stack.pop();
        }
        return retval;
    }
    // $ANTLR end "objectCreation"


    public static class literal_return extends ParserRuleReturnScope {
        EvaluationExpression tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "literal"
    // /home/arv/workspace/master/meteor/meteor-meteor/src/main/java/eu/stratosphere/meteor/Meteor.g:244:1: literal : (val= 'true' -> ^( EXPRESSION[\"ConstantExpression\"] ) |val= 'false' -> ^( EXPRESSION[\"ConstantExpression\"] ) |val= DECIMAL -> ^( EXPRESSION[\"ConstantExpression\"] ) |val= STRING -> ^( EXPRESSION[\"ConstantExpression\"] ) | (val= UINT |val= INTEGER ) -> ^( EXPRESSION[\"ConstantExpression\"] ) | 'null' ->);
    public final MeteorParser.literal_return literal() throws RecognitionException {
        MeteorParser.literal_return retval = new MeteorParser.literal_return();
        retval.start = input.LT(1);


        EvaluationExpression root_0 = null;

        Token val=null;
        Token string_literal86=null;

        EvaluationExpression val_tree=null;
        EvaluationExpression string_literal86_tree=null;
        RewriteRuleTokenStream stream_INTEGER=new RewriteRuleTokenStream(adaptor,"token INTEGER");
        RewriteRuleTokenStream stream_DECIMAL=new RewriteRuleTokenStream(adaptor,"token DECIMAL");
        RewriteRuleTokenStream stream_65=new RewriteRuleTokenStream(adaptor,"token 65");
        RewriteRuleTokenStream stream_62=new RewriteRuleTokenStream(adaptor,"token 62");
        RewriteRuleTokenStream stream_UINT=new RewriteRuleTokenStream(adaptor,"token UINT");
        RewriteRuleTokenStream stream_60=new RewriteRuleTokenStream(adaptor,"token 60");
        RewriteRuleTokenStream stream_STRING=new RewriteRuleTokenStream(adaptor,"token STRING");

         paraphrase.push("a literal"); 
        try {
            // /home/arv/workspace/master/meteor/meteor-meteor/src/main/java/eu/stratosphere/meteor/Meteor.g:247:2: (val= 'true' -> ^( EXPRESSION[\"ConstantExpression\"] ) |val= 'false' -> ^( EXPRESSION[\"ConstantExpression\"] ) |val= DECIMAL -> ^( EXPRESSION[\"ConstantExpression\"] ) |val= STRING -> ^( EXPRESSION[\"ConstantExpression\"] ) | (val= UINT |val= INTEGER ) -> ^( EXPRESSION[\"ConstantExpression\"] ) | 'null' ->)
            int alt46=6;
            switch ( input.LA(1) ) {
            case 65:
                {
                alt46=1;
                }
                break;
            case 60:
                {
                alt46=2;
                }
                break;
            case DECIMAL:
                {
                alt46=3;
                }
                break;
            case STRING:
                {
                alt46=4;
                }
                break;
            case INTEGER:
            case UINT:
                {
                alt46=5;
                }
                break;
            case 62:
                {
                alt46=6;
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("", 46, 0, input);

                throw nvae;

            }

            switch (alt46) {
                case 1 :
                    // /home/arv/workspace/master/meteor/meteor-meteor/src/main/java/eu/stratosphere/meteor/Meteor.g:247:4: val= 'true'
                    {
                    val=(Token)match(input,65,FOLLOW_65_in_literal1778); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_65.add(val);


                    // AST REWRITE
                    // elements: 
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    if ( state.backtracking==0 ) {

                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (EvaluationExpression)adaptor.nil();
                    // 247:15: -> ^( EXPRESSION[\"ConstantExpression\"] )
                    {
                        // /home/arv/workspace/master/meteor/meteor-meteor/src/main/java/eu/stratosphere/meteor/Meteor.g:247:18: ^( EXPRESSION[\"ConstantExpression\"] )
                        {
                        EvaluationExpression root_1 = (EvaluationExpression)adaptor.nil();
                        root_1 = (EvaluationExpression)adaptor.becomeRoot(
                        (EvaluationExpression)adaptor.create(EXPRESSION, "ConstantExpression")
                        , root_1);

                        adaptor.addChild(root_1,  Boolean.TRUE );

                        adaptor.addChild(root_0, root_1);
                        }

                    }


                    retval.tree = root_0;
                    }

                    }
                    break;
                case 2 :
                    // /home/arv/workspace/master/meteor/meteor-meteor/src/main/java/eu/stratosphere/meteor/Meteor.g:248:4: val= 'false'
                    {
                    val=(Token)match(input,60,FOLLOW_60_in_literal1794); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_60.add(val);


                    // AST REWRITE
                    // elements: 
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    if ( state.backtracking==0 ) {

                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (EvaluationExpression)adaptor.nil();
                    // 248:16: -> ^( EXPRESSION[\"ConstantExpression\"] )
                    {
                        // /home/arv/workspace/master/meteor/meteor-meteor/src/main/java/eu/stratosphere/meteor/Meteor.g:248:19: ^( EXPRESSION[\"ConstantExpression\"] )
                        {
                        EvaluationExpression root_1 = (EvaluationExpression)adaptor.nil();
                        root_1 = (EvaluationExpression)adaptor.becomeRoot(
                        (EvaluationExpression)adaptor.create(EXPRESSION, "ConstantExpression")
                        , root_1);

                        adaptor.addChild(root_1,  Boolean.FALSE );

                        adaptor.addChild(root_0, root_1);
                        }

                    }


                    retval.tree = root_0;
                    }

                    }
                    break;
                case 3 :
                    // /home/arv/workspace/master/meteor/meteor-meteor/src/main/java/eu/stratosphere/meteor/Meteor.g:249:4: val= DECIMAL
                    {
                    val=(Token)match(input,DECIMAL,FOLLOW_DECIMAL_in_literal1810); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_DECIMAL.add(val);


                    // AST REWRITE
                    // elements: 
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    if ( state.backtracking==0 ) {

                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (EvaluationExpression)adaptor.nil();
                    // 249:16: -> ^( EXPRESSION[\"ConstantExpression\"] )
                    {
                        // /home/arv/workspace/master/meteor/meteor-meteor/src/main/java/eu/stratosphere/meteor/Meteor.g:249:19: ^( EXPRESSION[\"ConstantExpression\"] )
                        {
                        EvaluationExpression root_1 = (EvaluationExpression)adaptor.nil();
                        root_1 = (EvaluationExpression)adaptor.becomeRoot(
                        (EvaluationExpression)adaptor.create(EXPRESSION, "ConstantExpression")
                        , root_1);

                        adaptor.addChild(root_1,  new BigDecimal((val!=null?val.getText():null)) );

                        adaptor.addChild(root_0, root_1);
                        }

                    }


                    retval.tree = root_0;
                    }

                    }
                    break;
                case 4 :
                    // /home/arv/workspace/master/meteor/meteor-meteor/src/main/java/eu/stratosphere/meteor/Meteor.g:250:4: val= STRING
                    {
                    val=(Token)match(input,STRING,FOLLOW_STRING_in_literal1826); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_STRING.add(val);


                    // AST REWRITE
                    // elements: 
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    if ( state.backtracking==0 ) {

                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (EvaluationExpression)adaptor.nil();
                    // 250:15: -> ^( EXPRESSION[\"ConstantExpression\"] )
                    {
                        // /home/arv/workspace/master/meteor/meteor-meteor/src/main/java/eu/stratosphere/meteor/Meteor.g:250:18: ^( EXPRESSION[\"ConstantExpression\"] )
                        {
                        EvaluationExpression root_1 = (EvaluationExpression)adaptor.nil();
                        root_1 = (EvaluationExpression)adaptor.becomeRoot(
                        (EvaluationExpression)adaptor.create(EXPRESSION, "ConstantExpression")
                        , root_1);

                        adaptor.addChild(root_1,  val.getText() );

                        adaptor.addChild(root_0, root_1);
                        }

                    }


                    retval.tree = root_0;
                    }

                    }
                    break;
                case 5 :
                    // /home/arv/workspace/master/meteor/meteor-meteor/src/main/java/eu/stratosphere/meteor/Meteor.g:251:5: (val= UINT |val= INTEGER )
                    {
                    // /home/arv/workspace/master/meteor/meteor-meteor/src/main/java/eu/stratosphere/meteor/Meteor.g:251:5: (val= UINT |val= INTEGER )
                    int alt45=2;
                    int LA45_0 = input.LA(1);

                    if ( (LA45_0==UINT) ) {
                        alt45=1;
                    }
                    else if ( (LA45_0==INTEGER) ) {
                        alt45=2;
                    }
                    else {
                        if (state.backtracking>0) {state.failed=true; return retval;}
                        NoViableAltException nvae =
                            new NoViableAltException("", 45, 0, input);

                        throw nvae;

                    }
                    switch (alt45) {
                        case 1 :
                            // /home/arv/workspace/master/meteor/meteor-meteor/src/main/java/eu/stratosphere/meteor/Meteor.g:251:6: val= UINT
                            {
                            val=(Token)match(input,UINT,FOLLOW_UINT_in_literal1844); if (state.failed) return retval; 
                            if ( state.backtracking==0 ) stream_UINT.add(val);


                            }
                            break;
                        case 2 :
                            // /home/arv/workspace/master/meteor/meteor-meteor/src/main/java/eu/stratosphere/meteor/Meteor.g:251:17: val= INTEGER
                            {
                            val=(Token)match(input,INTEGER,FOLLOW_INTEGER_in_literal1850); if (state.failed) return retval; 
                            if ( state.backtracking==0 ) stream_INTEGER.add(val);


                            }
                            break;

                    }


                    // AST REWRITE
                    // elements: 
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    if ( state.backtracking==0 ) {

                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (EvaluationExpression)adaptor.nil();
                    // 251:30: -> ^( EXPRESSION[\"ConstantExpression\"] )
                    {
                        // /home/arv/workspace/master/meteor/meteor-meteor/src/main/java/eu/stratosphere/meteor/Meteor.g:251:33: ^( EXPRESSION[\"ConstantExpression\"] )
                        {
                        EvaluationExpression root_1 = (EvaluationExpression)adaptor.nil();
                        root_1 = (EvaluationExpression)adaptor.becomeRoot(
                        (EvaluationExpression)adaptor.create(EXPRESSION, "ConstantExpression")
                        , root_1);

                        adaptor.addChild(root_1,  parseInt((val!=null?val.getText():null)) );

                        adaptor.addChild(root_0, root_1);
                        }

                    }


                    retval.tree = root_0;
                    }

                    }
                    break;
                case 6 :
                    // /home/arv/workspace/master/meteor/meteor-meteor/src/main/java/eu/stratosphere/meteor/Meteor.g:252:5: 'null'
                    {
                    string_literal86=(Token)match(input,62,FOLLOW_62_in_literal1866); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_62.add(string_literal86);


                    // AST REWRITE
                    // elements: 
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    if ( state.backtracking==0 ) {

                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (EvaluationExpression)adaptor.nil();
                    // 252:12: ->
                    {
                        adaptor.addChild(root_0,  ConstantExpression.NULL );

                    }


                    retval.tree = root_0;
                    }

                    }
                    break;

            }
            retval.stop = input.LT(-1);


            if ( state.backtracking==0 ) {

            retval.tree = (EvaluationExpression)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
            if ( state.backtracking==0 ) { paraphrase.pop(); }
        }

        catch (RecognitionException e) {
          throw e;
        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "literal"


    public static class streamIndexAccess_return extends ParserRuleReturnScope {
        EvaluationExpression tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "streamIndexAccess"
    // /home/arv/workspace/master/meteor/meteor-meteor/src/main/java/eu/stratosphere/meteor/Meteor.g:254:1: streamIndexAccess : op= VAR {...}? => '[' path= generalPathExpression ']' {...}? ->;
    public final MeteorParser.streamIndexAccess_return streamIndexAccess() throws RecognitionException {
        MeteorParser.streamIndexAccess_return retval = new MeteorParser.streamIndexAccess_return();
        retval.start = input.LT(1);


        EvaluationExpression root_0 = null;

        Token op=null;
        Token char_literal87=null;
        Token char_literal88=null;
        MeteorParser.generalPathExpression_return path =null;


        EvaluationExpression op_tree=null;
        EvaluationExpression char_literal87_tree=null;
        EvaluationExpression char_literal88_tree=null;
        RewriteRuleTokenStream stream_59=new RewriteRuleTokenStream(adaptor,"token 59");
        RewriteRuleTokenStream stream_58=new RewriteRuleTokenStream(adaptor,"token 58");
        RewriteRuleTokenStream stream_VAR=new RewriteRuleTokenStream(adaptor,"token VAR");
        RewriteRuleSubtreeStream stream_generalPathExpression=new RewriteRuleSubtreeStream(adaptor,"rule generalPathExpression");
        try {
            // /home/arv/workspace/master/meteor/meteor-meteor/src/main/java/eu/stratosphere/meteor/Meteor.g:255:3: (op= VAR {...}? => '[' path= generalPathExpression ']' {...}? ->)
            // /home/arv/workspace/master/meteor/meteor-meteor/src/main/java/eu/stratosphere/meteor/Meteor.g:255:5: op= VAR {...}? => '[' path= generalPathExpression ']' {...}?
            {
            op=(Token)match(input,VAR,FOLLOW_VAR_in_streamIndexAccess1882); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_VAR.add(op);


            if ( !(( getVariable(op) != null )) ) {
                if (state.backtracking>0) {state.failed=true; return retval;}
                throw new FailedPredicateException(input, "streamIndexAccess", " getVariable($op) != null ");
            }

            char_literal87=(Token)match(input,58,FOLLOW_58_in_streamIndexAccess1891); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_58.add(char_literal87);


            pushFollow(FOLLOW_generalPathExpression_in_streamIndexAccess1895);
            path=generalPathExpression();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) stream_generalPathExpression.add(path.getTree());

            char_literal88=(Token)match(input,59,FOLLOW_59_in_streamIndexAccess1897); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_59.add(char_literal88);


            if ( !(( !((path!=null?((EvaluationExpression)path.tree):null) instanceof ConstantExpression) )) ) {
                if (state.backtracking>0) {state.failed=true; return retval;}
                throw new FailedPredicateException(input, "streamIndexAccess", " !($path.tree instanceof ConstantExpression) ");
            }

            // AST REWRITE
            // elements: 
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            if ( state.backtracking==0 ) {

            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

            root_0 = (EvaluationExpression)adaptor.nil();
            // 257:3: ->
            {
                adaptor.addChild(root_0,  new StreamIndexExpression(getVariable(op).getStream(), (path!=null?((EvaluationExpression)path.tree):null)) );

            }


            retval.tree = root_0;
            }

            }

            retval.stop = input.LT(-1);


            if ( state.backtracking==0 ) {

            retval.tree = (EvaluationExpression)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }

        catch (RecognitionException e) {
          throw e;
        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "streamIndexAccess"


    public static class arrayCreation_return extends ParserRuleReturnScope {
        EvaluationExpression tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "arrayCreation"
    // /home/arv/workspace/master/meteor/meteor-meteor/src/main/java/eu/stratosphere/meteor/Meteor.g:259:1: arrayCreation : '[' elems+= expression ( ',' elems+= expression )* ( ',' )? ']' -> ^( EXPRESSION[\"ArrayCreation\"] ) ;
    public final MeteorParser.arrayCreation_return arrayCreation() throws RecognitionException {
        MeteorParser.arrayCreation_return retval = new MeteorParser.arrayCreation_return();
        retval.start = input.LT(1);


        EvaluationExpression root_0 = null;

        Token char_literal89=null;
        Token char_literal90=null;
        Token char_literal91=null;
        Token char_literal92=null;
        List list_elems=null;
        RuleReturnScope elems = null;
        EvaluationExpression char_literal89_tree=null;
        EvaluationExpression char_literal90_tree=null;
        EvaluationExpression char_literal91_tree=null;
        EvaluationExpression char_literal92_tree=null;
        RewriteRuleTokenStream stream_59=new RewriteRuleTokenStream(adaptor,"token 59");
        RewriteRuleTokenStream stream_43=new RewriteRuleTokenStream(adaptor,"token 43");
        RewriteRuleTokenStream stream_58=new RewriteRuleTokenStream(adaptor,"token 58");
        RewriteRuleSubtreeStream stream_expression=new RewriteRuleSubtreeStream(adaptor,"rule expression");
         paraphrase.push("a json array"); 
        try {
            // /home/arv/workspace/master/meteor/meteor-meteor/src/main/java/eu/stratosphere/meteor/Meteor.g:262:2: ( '[' elems+= expression ( ',' elems+= expression )* ( ',' )? ']' -> ^( EXPRESSION[\"ArrayCreation\"] ) )
            // /home/arv/workspace/master/meteor/meteor-meteor/src/main/java/eu/stratosphere/meteor/Meteor.g:262:5: '[' elems+= expression ( ',' elems+= expression )* ( ',' )? ']'
            {
            char_literal89=(Token)match(input,58,FOLLOW_58_in_arrayCreation1926); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_58.add(char_literal89);


            pushFollow(FOLLOW_expression_in_arrayCreation1930);
            elems=expression();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) stream_expression.add(elems.getTree());
            if (list_elems==null) list_elems=new ArrayList();
            list_elems.add(elems.getTree());


            // /home/arv/workspace/master/meteor/meteor-meteor/src/main/java/eu/stratosphere/meteor/Meteor.g:262:27: ( ',' elems+= expression )*
            loop47:
            do {
                int alt47=2;
                int LA47_0 = input.LA(1);

                if ( (LA47_0==43) ) {
                    int LA47_1 = input.LA(2);

                    if ( (LA47_1==DECIMAL||LA47_1==ID||LA47_1==INTEGER||(LA47_1 >= STRING && LA47_1 <= UINT)||LA47_1==VAR||LA47_1==35||LA47_1==39||LA47_1==42||LA47_1==45||LA47_1==58||LA47_1==60||(LA47_1 >= 62 && LA47_1 <= 63)||LA47_1==65||(LA47_1 >= 67 && LA47_1 <= 68)||LA47_1==71) ) {
                        alt47=1;
                    }


                }


                switch (alt47) {
            	case 1 :
            	    // /home/arv/workspace/master/meteor/meteor-meteor/src/main/java/eu/stratosphere/meteor/Meteor.g:262:28: ',' elems+= expression
            	    {
            	    char_literal90=(Token)match(input,43,FOLLOW_43_in_arrayCreation1933); if (state.failed) return retval; 
            	    if ( state.backtracking==0 ) stream_43.add(char_literal90);


            	    pushFollow(FOLLOW_expression_in_arrayCreation1937);
            	    elems=expression();

            	    state._fsp--;
            	    if (state.failed) return retval;
            	    if ( state.backtracking==0 ) stream_expression.add(elems.getTree());
            	    if (list_elems==null) list_elems=new ArrayList();
            	    list_elems.add(elems.getTree());


            	    }
            	    break;

            	default :
            	    break loop47;
                }
            } while (true);


            // /home/arv/workspace/master/meteor/meteor-meteor/src/main/java/eu/stratosphere/meteor/Meteor.g:262:52: ( ',' )?
            int alt48=2;
            int LA48_0 = input.LA(1);

            if ( (LA48_0==43) ) {
                alt48=1;
            }
            switch (alt48) {
                case 1 :
                    // /home/arv/workspace/master/meteor/meteor-meteor/src/main/java/eu/stratosphere/meteor/Meteor.g:262:52: ','
                    {
                    char_literal91=(Token)match(input,43,FOLLOW_43_in_arrayCreation1941); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_43.add(char_literal91);


                    }
                    break;

            }


            char_literal92=(Token)match(input,59,FOLLOW_59_in_arrayCreation1944); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_59.add(char_literal92);


            // AST REWRITE
            // elements: 
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            if ( state.backtracking==0 ) {

            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

            root_0 = (EvaluationExpression)adaptor.nil();
            // 262:61: -> ^( EXPRESSION[\"ArrayCreation\"] )
            {
                // /home/arv/workspace/master/meteor/meteor-meteor/src/main/java/eu/stratosphere/meteor/Meteor.g:262:64: ^( EXPRESSION[\"ArrayCreation\"] )
                {
                EvaluationExpression root_1 = (EvaluationExpression)adaptor.nil();
                root_1 = (EvaluationExpression)adaptor.becomeRoot(
                (EvaluationExpression)adaptor.create(EXPRESSION, "ArrayCreation")
                , root_1);

                adaptor.addChild(root_1,  list_elems.toArray(new EvaluationExpression[list_elems.size()]) );

                adaptor.addChild(root_0, root_1);
                }

            }


            retval.tree = root_0;
            }

            }

            retval.stop = input.LT(-1);


            if ( state.backtracking==0 ) {

            retval.tree = (EvaluationExpression)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
            if ( state.backtracking==0 ) { paraphrase.pop(); }
        }

        catch (RecognitionException e) {
          throw e;
        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "arrayCreation"


    protected static class operator_scope {
        Operator<?> result;
        int numInputs;
        Map<JsonStream, List<ExpressionTag>> inputTags;
    }
    protected Stack operator_stack = new Stack();


    public static class operator_return extends ParserRuleReturnScope {
        public Operator<?> op=null;
        EvaluationExpression tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "operator"
    // /home/arv/workspace/master/meteor/meteor-meteor/src/main/java/eu/stratosphere/meteor/Meteor.g:264:1: operator returns [Operator<?> op=null] : opRule= ( readOperator | writeOperator | genericOperator ) ;
    public final MeteorParser.operator_return operator() throws RecognitionException {
        operator_stack.push(new operator_scope());
        MeteorParser.operator_return retval = new MeteorParser.operator_return();
        retval.start = input.LT(1);


        EvaluationExpression root_0 = null;

        Token opRule=null;
        MeteorParser.readOperator_return readOperator93 =null;

        MeteorParser.writeOperator_return writeOperator94 =null;

        MeteorParser.genericOperator_return genericOperator95 =null;


        EvaluationExpression opRule_tree=null;


          if(state.backtracking == 0) 
        	  addScope();
        	((operator_scope)operator_stack.peek()).inputTags = new IdentityHashMap<JsonStream, List<ExpressionTag>>();

        try {
            // /home/arv/workspace/master/meteor/meteor-meteor/src/main/java/eu/stratosphere/meteor/Meteor.g:277:2: (opRule= ( readOperator | writeOperator | genericOperator ) )
            // /home/arv/workspace/master/meteor/meteor-meteor/src/main/java/eu/stratosphere/meteor/Meteor.g:277:4: opRule= ( readOperator | writeOperator | genericOperator )
            {
            root_0 = (EvaluationExpression)adaptor.nil();


            // /home/arv/workspace/master/meteor/meteor-meteor/src/main/java/eu/stratosphere/meteor/Meteor.g:277:11: ( readOperator | writeOperator | genericOperator )
            int alt49=3;
            switch ( input.LA(1) ) {
            case 63:
                {
                alt49=1;
                }
                break;
            case 67:
                {
                alt49=2;
                }
                break;
            case ID:
                {
                alt49=3;
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("", 49, 0, input);

                throw nvae;

            }

            switch (alt49) {
                case 1 :
                    // /home/arv/workspace/master/meteor/meteor-meteor/src/main/java/eu/stratosphere/meteor/Meteor.g:277:12: readOperator
                    {
                    pushFollow(FOLLOW_readOperator_in_operator1981);
                    readOperator93=readOperator();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, readOperator93.getTree());

                    }
                    break;
                case 2 :
                    // /home/arv/workspace/master/meteor/meteor-meteor/src/main/java/eu/stratosphere/meteor/Meteor.g:277:27: writeOperator
                    {
                    pushFollow(FOLLOW_writeOperator_in_operator1985);
                    writeOperator94=writeOperator();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, writeOperator94.getTree());

                    }
                    break;
                case 3 :
                    // /home/arv/workspace/master/meteor/meteor-meteor/src/main/java/eu/stratosphere/meteor/Meteor.g:277:43: genericOperator
                    {
                    pushFollow(FOLLOW_genericOperator_in_operator1989);
                    genericOperator95=genericOperator();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, genericOperator95.getTree());

                    }
                    break;

            }


            if ( state.backtracking==0 ) { 
              retval.op = ((operator_scope)operator_stack.peek()).result;
            }

            }

            retval.stop = input.LT(-1);


            if ( state.backtracking==0 ) {

            retval.tree = (EvaluationExpression)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
            if ( state.backtracking==0 ) {
              removeScope();
            }
        }

        catch (RecognitionException e) {
          throw e;
        }

        finally {
        	// do for sure before leaving
            operator_stack.pop();
        }
        return retval;
    }
    // $ANTLR end "operator"


    public static class readOperator_return extends ParserRuleReturnScope {
        EvaluationExpression tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "readOperator"
    // /home/arv/workspace/master/meteor/meteor-meteor/src/main/java/eu/stratosphere/meteor/Meteor.g:282:1: readOperator : 'read' 'from' ( (loc= ID )? file= STRING |loc= ID '(' file= STRING ')' ) ->;
    public final MeteorParser.readOperator_return readOperator() throws RecognitionException {
        MeteorParser.readOperator_return retval = new MeteorParser.readOperator_return();
        retval.start = input.LT(1);


        EvaluationExpression root_0 = null;

        Token loc=null;
        Token file=null;
        Token string_literal96=null;
        Token string_literal97=null;
        Token char_literal98=null;
        Token char_literal99=null;

        EvaluationExpression loc_tree=null;
        EvaluationExpression file_tree=null;
        EvaluationExpression string_literal96_tree=null;
        EvaluationExpression string_literal97_tree=null;
        EvaluationExpression char_literal98_tree=null;
        EvaluationExpression char_literal99_tree=null;
        RewriteRuleTokenStream stream_40=new RewriteRuleTokenStream(adaptor,"token 40");
        RewriteRuleTokenStream stream_ID=new RewriteRuleTokenStream(adaptor,"token ID");
        RewriteRuleTokenStream stream_63=new RewriteRuleTokenStream(adaptor,"token 63");
        RewriteRuleTokenStream stream_39=new RewriteRuleTokenStream(adaptor,"token 39");
        RewriteRuleTokenStream stream_61=new RewriteRuleTokenStream(adaptor,"token 61");
        RewriteRuleTokenStream stream_STRING=new RewriteRuleTokenStream(adaptor,"token STRING");

        try {
            // /home/arv/workspace/master/meteor/meteor-meteor/src/main/java/eu/stratosphere/meteor/Meteor.g:283:2: ( 'read' 'from' ( (loc= ID )? file= STRING |loc= ID '(' file= STRING ')' ) ->)
            // /home/arv/workspace/master/meteor/meteor-meteor/src/main/java/eu/stratosphere/meteor/Meteor.g:283:4: 'read' 'from' ( (loc= ID )? file= STRING |loc= ID '(' file= STRING ')' )
            {
            string_literal96=(Token)match(input,63,FOLLOW_63_in_readOperator2003); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_63.add(string_literal96);


            string_literal97=(Token)match(input,61,FOLLOW_61_in_readOperator2005); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_61.add(string_literal97);


            // /home/arv/workspace/master/meteor/meteor-meteor/src/main/java/eu/stratosphere/meteor/Meteor.g:283:18: ( (loc= ID )? file= STRING |loc= ID '(' file= STRING ')' )
            int alt51=2;
            int LA51_0 = input.LA(1);

            if ( (LA51_0==ID) ) {
                int LA51_1 = input.LA(2);

                if ( (LA51_1==39) ) {
                    alt51=2;
                }
                else if ( (LA51_1==STRING) ) {
                    alt51=1;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return retval;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 51, 1, input);

                    throw nvae;

                }
            }
            else if ( (LA51_0==STRING) ) {
                alt51=1;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("", 51, 0, input);

                throw nvae;

            }
            switch (alt51) {
                case 1 :
                    // /home/arv/workspace/master/meteor/meteor-meteor/src/main/java/eu/stratosphere/meteor/Meteor.g:283:19: (loc= ID )? file= STRING
                    {
                    // /home/arv/workspace/master/meteor/meteor-meteor/src/main/java/eu/stratosphere/meteor/Meteor.g:283:22: (loc= ID )?
                    int alt50=2;
                    int LA50_0 = input.LA(1);

                    if ( (LA50_0==ID) ) {
                        alt50=1;
                    }
                    switch (alt50) {
                        case 1 :
                            // /home/arv/workspace/master/meteor/meteor-meteor/src/main/java/eu/stratosphere/meteor/Meteor.g:283:22: loc= ID
                            {
                            loc=(Token)match(input,ID,FOLLOW_ID_in_readOperator2010); if (state.failed) return retval; 
                            if ( state.backtracking==0 ) stream_ID.add(loc);


                            }
                            break;

                    }


                    file=(Token)match(input,STRING,FOLLOW_STRING_in_readOperator2015); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_STRING.add(file);


                    }
                    break;
                case 2 :
                    // /home/arv/workspace/master/meteor/meteor-meteor/src/main/java/eu/stratosphere/meteor/Meteor.g:283:41: loc= ID '(' file= STRING ')'
                    {
                    loc=(Token)match(input,ID,FOLLOW_ID_in_readOperator2021); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_ID.add(loc);


                    char_literal98=(Token)match(input,39,FOLLOW_39_in_readOperator2023); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_39.add(char_literal98);


                    file=(Token)match(input,STRING,FOLLOW_STRING_in_readOperator2027); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_STRING.add(file);


                    char_literal99=(Token)match(input,40,FOLLOW_40_in_readOperator2029); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_40.add(char_literal99);


                    }
                    break;

            }


            if ( state.backtracking==0 ) { ((operator_scope)operator_stack.peek()).result = new Source(JsonInputFormat.class, (file!=null?file.getText():null)); }

            // AST REWRITE
            // elements: 
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            if ( state.backtracking==0 ) {

            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

            root_0 = (EvaluationExpression)adaptor.nil();
            // 283:140: ->
            {
                root_0 = null;
            }


            retval.tree = root_0;
            }

            }

            retval.stop = input.LT(-1);


            if ( state.backtracking==0 ) {

            retval.tree = (EvaluationExpression)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }

        catch (RecognitionException e) {
          throw e;
        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "readOperator"


    public static class writeOperator_return extends ParserRuleReturnScope {
        EvaluationExpression tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "writeOperator"
    // /home/arv/workspace/master/meteor/meteor-meteor/src/main/java/eu/stratosphere/meteor/Meteor.g:285:1: writeOperator : 'write' from= VAR 'to' ( (loc= ID )? file= STRING |loc= ID '(' file= STRING ')' ) ->;
    public final MeteorParser.writeOperator_return writeOperator() throws RecognitionException {
        MeteorParser.writeOperator_return retval = new MeteorParser.writeOperator_return();
        retval.start = input.LT(1);


        EvaluationExpression root_0 = null;

        Token from=null;
        Token loc=null;
        Token file=null;
        Token string_literal100=null;
        Token string_literal101=null;
        Token char_literal102=null;
        Token char_literal103=null;

        EvaluationExpression from_tree=null;
        EvaluationExpression loc_tree=null;
        EvaluationExpression file_tree=null;
        EvaluationExpression string_literal100_tree=null;
        EvaluationExpression string_literal101_tree=null;
        EvaluationExpression char_literal102_tree=null;
        EvaluationExpression char_literal103_tree=null;
        RewriteRuleTokenStream stream_67=new RewriteRuleTokenStream(adaptor,"token 67");
        RewriteRuleTokenStream stream_VAR=new RewriteRuleTokenStream(adaptor,"token VAR");
        RewriteRuleTokenStream stream_40=new RewriteRuleTokenStream(adaptor,"token 40");
        RewriteRuleTokenStream stream_64=new RewriteRuleTokenStream(adaptor,"token 64");
        RewriteRuleTokenStream stream_ID=new RewriteRuleTokenStream(adaptor,"token ID");
        RewriteRuleTokenStream stream_39=new RewriteRuleTokenStream(adaptor,"token 39");
        RewriteRuleTokenStream stream_STRING=new RewriteRuleTokenStream(adaptor,"token STRING");

        try {
            // /home/arv/workspace/master/meteor/meteor-meteor/src/main/java/eu/stratosphere/meteor/Meteor.g:286:2: ( 'write' from= VAR 'to' ( (loc= ID )? file= STRING |loc= ID '(' file= STRING ')' ) ->)
            // /home/arv/workspace/master/meteor/meteor-meteor/src/main/java/eu/stratosphere/meteor/Meteor.g:286:4: 'write' from= VAR 'to' ( (loc= ID )? file= STRING |loc= ID '(' file= STRING ')' )
            {
            string_literal100=(Token)match(input,67,FOLLOW_67_in_writeOperator2043); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_67.add(string_literal100);


            from=(Token)match(input,VAR,FOLLOW_VAR_in_writeOperator2047); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_VAR.add(from);


            string_literal101=(Token)match(input,64,FOLLOW_64_in_writeOperator2049); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_64.add(string_literal101);


            // /home/arv/workspace/master/meteor/meteor-meteor/src/main/java/eu/stratosphere/meteor/Meteor.g:286:26: ( (loc= ID )? file= STRING |loc= ID '(' file= STRING ')' )
            int alt53=2;
            int LA53_0 = input.LA(1);

            if ( (LA53_0==ID) ) {
                int LA53_1 = input.LA(2);

                if ( (LA53_1==39) ) {
                    alt53=2;
                }
                else if ( (LA53_1==STRING) ) {
                    alt53=1;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return retval;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 53, 1, input);

                    throw nvae;

                }
            }
            else if ( (LA53_0==STRING) ) {
                alt53=1;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("", 53, 0, input);

                throw nvae;

            }
            switch (alt53) {
                case 1 :
                    // /home/arv/workspace/master/meteor/meteor-meteor/src/main/java/eu/stratosphere/meteor/Meteor.g:286:27: (loc= ID )? file= STRING
                    {
                    // /home/arv/workspace/master/meteor/meteor-meteor/src/main/java/eu/stratosphere/meteor/Meteor.g:286:30: (loc= ID )?
                    int alt52=2;
                    int LA52_0 = input.LA(1);

                    if ( (LA52_0==ID) ) {
                        alt52=1;
                    }
                    switch (alt52) {
                        case 1 :
                            // /home/arv/workspace/master/meteor/meteor-meteor/src/main/java/eu/stratosphere/meteor/Meteor.g:286:30: loc= ID
                            {
                            loc=(Token)match(input,ID,FOLLOW_ID_in_writeOperator2054); if (state.failed) return retval; 
                            if ( state.backtracking==0 ) stream_ID.add(loc);


                            }
                            break;

                    }


                    file=(Token)match(input,STRING,FOLLOW_STRING_in_writeOperator2059); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_STRING.add(file);


                    }
                    break;
                case 2 :
                    // /home/arv/workspace/master/meteor/meteor-meteor/src/main/java/eu/stratosphere/meteor/Meteor.g:286:49: loc= ID '(' file= STRING ')'
                    {
                    loc=(Token)match(input,ID,FOLLOW_ID_in_writeOperator2065); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_ID.add(loc);


                    char_literal102=(Token)match(input,39,FOLLOW_39_in_writeOperator2067); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_39.add(char_literal102);


                    file=(Token)match(input,STRING,FOLLOW_STRING_in_writeOperator2071); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_STRING.add(file);


                    char_literal103=(Token)match(input,40,FOLLOW_40_in_writeOperator2073); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_40.add(char_literal103);


                    }
                    break;

            }


            if ( state.backtracking==0 ) { 
            	Sink sink = new Sink(JsonOutputFormat.class, (file!=null?file.getText():null));
              ((operator_scope)operator_stack.peek()).result = sink;
              sink.setInputs(getVariable(from).getStream());
              this.sinks.add(sink);
            }

            // AST REWRITE
            // elements: 
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            if ( state.backtracking==0 ) {

            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

            root_0 = (EvaluationExpression)adaptor.nil();
            // 292:3: ->
            {
                root_0 = null;
            }


            retval.tree = root_0;
            }

            }

            retval.stop = input.LT(-1);


            if ( state.backtracking==0 ) {

            retval.tree = (EvaluationExpression)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }

        catch (RecognitionException e) {
          throw e;
        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "writeOperator"


    protected static class genericOperator_scope {
        OperatorInfo<?> operatorInfo;
    }
    protected Stack genericOperator_stack = new Stack();


    public static class genericOperator_return extends ParserRuleReturnScope {
        EvaluationExpression tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "genericOperator"
    // /home/arv/workspace/master/meteor/meteor-meteor/src/main/java/eu/stratosphere/meteor/Meteor.g:294:1: genericOperator : (packageName= ID ':' )? name= ID {...}? => ( operatorFlag )* ( ( '[' )=> arrayInput | ( VAR )=> input ( ( ',' )=> ',' input )* ) ( operatorOption )* ->;
    public final MeteorParser.genericOperator_return genericOperator() throws RecognitionException {
        genericOperator_stack.push(new genericOperator_scope());
        MeteorParser.genericOperator_return retval = new MeteorParser.genericOperator_return();
        retval.start = input.LT(1);


        EvaluationExpression root_0 = null;

        Token packageName=null;
        Token name=null;
        Token char_literal104=null;
        Token char_literal108=null;
        MeteorParser.operatorFlag_return operatorFlag105 =null;

        MeteorParser.arrayInput_return arrayInput106 =null;

        MeteorParser.input_return input107 =null;

        MeteorParser.input_return input109 =null;

        MeteorParser.operatorOption_return operatorOption110 =null;


        EvaluationExpression packageName_tree=null;
        EvaluationExpression name_tree=null;
        EvaluationExpression char_literal104_tree=null;
        EvaluationExpression char_literal108_tree=null;
        RewriteRuleTokenStream stream_48=new RewriteRuleTokenStream(adaptor,"token 48");
        RewriteRuleTokenStream stream_43=new RewriteRuleTokenStream(adaptor,"token 43");
        RewriteRuleTokenStream stream_ID=new RewriteRuleTokenStream(adaptor,"token ID");
        RewriteRuleSubtreeStream stream_operatorOption=new RewriteRuleSubtreeStream(adaptor,"rule operatorOption");
        RewriteRuleSubtreeStream stream_input=new RewriteRuleSubtreeStream(adaptor,"rule input");
        RewriteRuleSubtreeStream stream_operatorFlag=new RewriteRuleSubtreeStream(adaptor,"rule operatorFlag");
        RewriteRuleSubtreeStream stream_arrayInput=new RewriteRuleSubtreeStream(adaptor,"rule arrayInput");
        try {
            // /home/arv/workspace/master/meteor/meteor-meteor/src/main/java/eu/stratosphere/meteor/Meteor.g:297:3: ( (packageName= ID ':' )? name= ID {...}? => ( operatorFlag )* ( ( '[' )=> arrayInput | ( VAR )=> input ( ( ',' )=> ',' input )* ) ( operatorOption )* ->)
            // /home/arv/workspace/master/meteor/meteor-meteor/src/main/java/eu/stratosphere/meteor/Meteor.g:297:5: (packageName= ID ':' )? name= ID {...}? => ( operatorFlag )* ( ( '[' )=> arrayInput | ( VAR )=> input ( ( ',' )=> ',' input )* ) ( operatorOption )*
            {
            // /home/arv/workspace/master/meteor/meteor-meteor/src/main/java/eu/stratosphere/meteor/Meteor.g:297:5: (packageName= ID ':' )?
            int alt54=2;
            int LA54_0 = input.LA(1);

            if ( (LA54_0==ID) ) {
                int LA54_1 = input.LA(2);

                if ( (LA54_1==48) ) {
                    alt54=1;
                }
            }
            switch (alt54) {
                case 1 :
                    // /home/arv/workspace/master/meteor/meteor-meteor/src/main/java/eu/stratosphere/meteor/Meteor.g:297:6: packageName= ID ':'
                    {
                    packageName=(Token)match(input,ID,FOLLOW_ID_in_genericOperator2094); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_ID.add(packageName);


                    char_literal104=(Token)match(input,48,FOLLOW_48_in_genericOperator2096); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_48.add(char_literal104);


                    }
                    break;

            }


            name=(Token)match(input,ID,FOLLOW_ID_in_genericOperator2102); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_ID.add(name);


            if ( !(( (((genericOperator_scope)genericOperator_stack.peek()).operatorInfo = findOperatorGreedily((packageName!=null?packageName.getText():null), name)) != null )) ) {
                if (state.backtracking>0) {state.failed=true; return retval;}
                throw new FailedPredicateException(input, "genericOperator", " ($genericOperator::operatorInfo = findOperatorGreedily($packageName.text, $name)) != null ");
            }

            if ( state.backtracking==0 ) { ((operator_scope)operator_stack.peek()).result = ((genericOperator_scope)genericOperator_stack.peek()).operatorInfo.newInstance(); }

            // /home/arv/workspace/master/meteor/meteor-meteor/src/main/java/eu/stratosphere/meteor/Meteor.g:299:1: ( operatorFlag )*
            loop55:
            do {
                int alt55=2;
                int LA55_0 = input.LA(1);

                if ( (LA55_0==ID) ) {
                    alt55=1;
                }


                switch (alt55) {
            	case 1 :
            	    // /home/arv/workspace/master/meteor/meteor-meteor/src/main/java/eu/stratosphere/meteor/Meteor.g:299:1: operatorFlag
            	    {
            	    pushFollow(FOLLOW_operatorFlag_in_genericOperator2110);
            	    operatorFlag105=operatorFlag();

            	    state._fsp--;
            	    if (state.failed) return retval;
            	    if ( state.backtracking==0 ) stream_operatorFlag.add(operatorFlag105.getTree());

            	    }
            	    break;

            	default :
            	    break loop55;
                }
            } while (true);


            // /home/arv/workspace/master/meteor/meteor-meteor/src/main/java/eu/stratosphere/meteor/Meteor.g:300:2: ( ( '[' )=> arrayInput | ( VAR )=> input ( ( ',' )=> ',' input )* )
            int alt57=2;
            int LA57_0 = input.LA(1);

            if ( (LA57_0==58) && (synpred15_Meteor())) {
                alt57=1;
            }
            else if ( (LA57_0==VAR) && (synpred16_Meteor())) {
                alt57=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("", 57, 0, input);

                throw nvae;

            }
            switch (alt57) {
                case 1 :
                    // /home/arv/workspace/master/meteor/meteor-meteor/src/main/java/eu/stratosphere/meteor/Meteor.g:300:2: ( '[' )=> arrayInput
                    {
                    pushFollow(FOLLOW_arrayInput_in_genericOperator2119);
                    arrayInput106=arrayInput();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_arrayInput.add(arrayInput106.getTree());

                    }
                    break;
                case 2 :
                    // /home/arv/workspace/master/meteor/meteor-meteor/src/main/java/eu/stratosphere/meteor/Meteor.g:300:23: ( VAR )=> input ( ( ',' )=> ',' input )*
                    {
                    pushFollow(FOLLOW_input_in_genericOperator2128);
                    input107=input();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_input.add(input107.getTree());

                    // /home/arv/workspace/master/meteor/meteor-meteor/src/main/java/eu/stratosphere/meteor/Meteor.g:300:37: ( ( ',' )=> ',' input )*
                    loop56:
                    do {
                        int alt56=2;
                        int LA56_0 = input.LA(1);

                        if ( (LA56_0==43) ) {
                            int LA56_2 = input.LA(2);

                            if ( (LA56_2==VAR) ) {
                                int LA56_3 = input.LA(3);

                                if ( (synpred17_Meteor()) ) {
                                    alt56=1;
                                }


                            }


                        }


                        switch (alt56) {
                    	case 1 :
                    	    // /home/arv/workspace/master/meteor/meteor-meteor/src/main/java/eu/stratosphere/meteor/Meteor.g:300:38: ( ',' )=> ',' input
                    	    {
                    	    char_literal108=(Token)match(input,43,FOLLOW_43_in_genericOperator2136); if (state.failed) return retval; 
                    	    if ( state.backtracking==0 ) stream_43.add(char_literal108);


                    	    pushFollow(FOLLOW_input_in_genericOperator2138);
                    	    input109=input();

                    	    state._fsp--;
                    	    if (state.failed) return retval;
                    	    if ( state.backtracking==0 ) stream_input.add(input109.getTree());

                    	    }
                    	    break;

                    	default :
                    	    break loop56;
                        }
                    } while (true);


                    }
                    break;

            }


            // /home/arv/workspace/master/meteor/meteor-meteor/src/main/java/eu/stratosphere/meteor/Meteor.g:301:1: ( operatorOption )*
            loop58:
            do {
                int alt58=2;
                int LA58_0 = input.LA(1);

                if ( (LA58_0==ID) ) {
                    alt58=1;
                }


                switch (alt58) {
            	case 1 :
            	    // /home/arv/workspace/master/meteor/meteor-meteor/src/main/java/eu/stratosphere/meteor/Meteor.g:301:1: operatorOption
            	    {
            	    pushFollow(FOLLOW_operatorOption_in_genericOperator2143);
            	    operatorOption110=operatorOption();

            	    state._fsp--;
            	    if (state.failed) return retval;
            	    if ( state.backtracking==0 ) stream_operatorOption.add(operatorOption110.getTree());

            	    }
            	    break;

            	default :
            	    break loop58;
                }
            } while (true);


            // AST REWRITE
            // elements: 
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            if ( state.backtracking==0 ) {

            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

            root_0 = (EvaluationExpression)adaptor.nil();
            // 301:17: ->
            {
                root_0 = null;
            }


            retval.tree = root_0;
            }

            }

            retval.stop = input.LT(-1);


            if ( state.backtracking==0 ) {

            retval.tree = (EvaluationExpression)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }

        catch (RecognitionException e) {
          throw e;
        }

        finally {
        	// do for sure before leaving
            genericOperator_stack.pop();
        }
        return retval;
    }
    // $ANTLR end "genericOperator"


    protected static class operatorOption_scope {
        OperatorInfo.OperatorPropertyInfo property;
    }
    protected Stack operatorOption_stack = new Stack();


    public static class operatorOption_return extends ParserRuleReturnScope {
        EvaluationExpression tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "operatorOption"
    // /home/arv/workspace/master/meteor/meteor-meteor/src/main/java/eu/stratosphere/meteor/Meteor.g:303:1: operatorOption : name= ID expr= contextAwareExpression[null] ->;
    public final MeteorParser.operatorOption_return operatorOption() throws RecognitionException {
        operatorOption_stack.push(new operatorOption_scope());
        MeteorParser.operatorOption_return retval = new MeteorParser.operatorOption_return();
        retval.start = input.LT(1);


        EvaluationExpression root_0 = null;

        Token name=null;
        MeteorParser.contextAwareExpression_return expr =null;


        EvaluationExpression name_tree=null;
        RewriteRuleTokenStream stream_ID=new RewriteRuleTokenStream(adaptor,"token ID");
        RewriteRuleSubtreeStream stream_contextAwareExpression=new RewriteRuleSubtreeStream(adaptor,"rule contextAwareExpression");
        try {
            // /home/arv/workspace/master/meteor/meteor-meteor/src/main/java/eu/stratosphere/meteor/Meteor.g:306:3: (name= ID expr= contextAwareExpression[null] ->)
            // /home/arv/workspace/master/meteor/meteor-meteor/src/main/java/eu/stratosphere/meteor/Meteor.g:307:3: name= ID expr= contextAwareExpression[null]
            {
            name=(Token)match(input,ID,FOLLOW_ID_in_operatorOption2165); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_ID.add(name);


            if ( state.backtracking==0 ) { ((operatorOption_scope)operatorOption_stack.peek()).property = findOperatorPropertyRelunctantly(((genericOperator_scope)genericOperator_stack.peek()).operatorInfo, name); }

            pushFollow(FOLLOW_contextAwareExpression_in_operatorOption2174);
            expr=contextAwareExpression(null);

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) stream_contextAwareExpression.add(expr.getTree());

            if ( state.backtracking==0 ) { ((operatorOption_scope)operatorOption_stack.peek()).property.setValue(((operator_scope)operator_stack.peek()).result, (expr!=null?((EvaluationExpression)expr.tree):null)); }

            // AST REWRITE
            // elements: 
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            if ( state.backtracking==0 ) {

            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

            root_0 = (EvaluationExpression)adaptor.nil();
            // 309:108: ->
            {
                root_0 = null;
            }


            retval.tree = root_0;
            }

            }

            retval.stop = input.LT(-1);


            if ( state.backtracking==0 ) {

            retval.tree = (EvaluationExpression)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }

        catch (RecognitionException e) {
          throw e;
        }

        finally {
        	// do for sure before leaving
            operatorOption_stack.pop();
        }
        return retval;
    }
    // $ANTLR end "operatorOption"


    protected static class operatorFlag_scope {
        OperatorInfo.OperatorPropertyInfo property;
    }
    protected Stack operatorFlag_stack = new Stack();


    public static class operatorFlag_return extends ParserRuleReturnScope {
        EvaluationExpression tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "operatorFlag"
    // /home/arv/workspace/master/meteor/meteor-meteor/src/main/java/eu/stratosphere/meteor/Meteor.g:311:1: operatorFlag : name= ID {...}? ->;
    public final MeteorParser.operatorFlag_return operatorFlag() throws RecognitionException {
        operatorFlag_stack.push(new operatorFlag_scope());
        MeteorParser.operatorFlag_return retval = new MeteorParser.operatorFlag_return();
        retval.start = input.LT(1);


        EvaluationExpression root_0 = null;

        Token name=null;

        EvaluationExpression name_tree=null;
        RewriteRuleTokenStream stream_ID=new RewriteRuleTokenStream(adaptor,"token ID");

        try {
            // /home/arv/workspace/master/meteor/meteor-meteor/src/main/java/eu/stratosphere/meteor/Meteor.g:315:3: (name= ID {...}? ->)
            // /home/arv/workspace/master/meteor/meteor-meteor/src/main/java/eu/stratosphere/meteor/Meteor.g:315:5: name= ID {...}?
            {
            name=(Token)match(input,ID,FOLLOW_ID_in_operatorFlag2195); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_ID.add(name);


            if ( !(( (((operatorFlag_scope)operatorFlag_stack.peek()).property = findOperatorPropertyRelunctantly(((genericOperator_scope)genericOperator_stack.peek()).operatorInfo, name)) != null )) ) {
                if (state.backtracking>0) {state.failed=true; return retval;}
                throw new FailedPredicateException(input, "operatorFlag", " ($operatorFlag::property = findOperatorPropertyRelunctantly($genericOperator::operatorInfo, $name)) != null ");
            }

            if ( state.backtracking==0 ) { if(!((operatorFlag_scope)operatorFlag_stack.peek()).property.isFlag())
                throw new QueryParserException(String.format("Property %s is not a flag", (name!=null?name.getText():null)), name);
              ((operatorFlag_scope)operatorFlag_stack.peek()).property.setValue(((operator_scope)operator_stack.peek()).result, true); }

            // AST REWRITE
            // elements: 
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            if ( state.backtracking==0 ) {

            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

            root_0 = (EvaluationExpression)adaptor.nil();
            // 318:64: ->
            {
                root_0 = null;
            }


            retval.tree = root_0;
            }

            }

            retval.stop = input.LT(-1);


            if ( state.backtracking==0 ) {

            retval.tree = (EvaluationExpression)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }

        catch (RecognitionException e) {
          throw e;
        }

        finally {
        	// do for sure before leaving
            operatorFlag_stack.pop();
        }
        return retval;
    }
    // $ANTLR end "operatorFlag"


    protected static class input_scope {
        OperatorInfo.InputPropertyInfo inputProperty;
    }
    protected Stack input_stack = new Stack();


    public static class input_return extends ParserRuleReturnScope {
        EvaluationExpression tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "input"
    // /home/arv/workspace/master/meteor/meteor-meteor/src/main/java/eu/stratosphere/meteor/Meteor.g:320:1: input : (name= VAR IN )? from= VAR ({...}? =>expr= contextAwareExpression[new InputSelection($operator::numInputs - 1)] )? ->;
    public final MeteorParser.input_return input() throws RecognitionException {
        input_stack.push(new input_scope());
        MeteorParser.input_return retval = new MeteorParser.input_return();
        retval.start = input.LT(1);


        EvaluationExpression root_0 = null;

        Token name=null;
        Token from=null;
        Token IN111=null;
        MeteorParser.contextAwareExpression_return expr =null;


        EvaluationExpression name_tree=null;
        EvaluationExpression from_tree=null;
        EvaluationExpression IN111_tree=null;
        RewriteRuleTokenStream stream_VAR=new RewriteRuleTokenStream(adaptor,"token VAR");
        RewriteRuleTokenStream stream_IN=new RewriteRuleTokenStream(adaptor,"token IN");
        RewriteRuleSubtreeStream stream_contextAwareExpression=new RewriteRuleSubtreeStream(adaptor,"rule contextAwareExpression");
        try {
            // /home/arv/workspace/master/meteor/meteor-meteor/src/main/java/eu/stratosphere/meteor/Meteor.g:323:3: ( (name= VAR IN )? from= VAR ({...}? =>expr= contextAwareExpression[new InputSelection($operator::numInputs - 1)] )? ->)
            // /home/arv/workspace/master/meteor/meteor-meteor/src/main/java/eu/stratosphere/meteor/Meteor.g:323:5: (name= VAR IN )? from= VAR ({...}? =>expr= contextAwareExpression[new InputSelection($operator::numInputs - 1)] )?
            {
            // /home/arv/workspace/master/meteor/meteor-meteor/src/main/java/eu/stratosphere/meteor/Meteor.g:323:5: (name= VAR IN )?
            int alt59=2;
            int LA59_0 = input.LA(1);

            if ( (LA59_0==VAR) ) {
                int LA59_1 = input.LA(2);

                if ( (LA59_1==IN) ) {
                    alt59=1;
                }
            }
            switch (alt59) {
                case 1 :
                    // /home/arv/workspace/master/meteor/meteor-meteor/src/main/java/eu/stratosphere/meteor/Meteor.g:323:6: name= VAR IN
                    {
                    name=(Token)match(input,VAR,FOLLOW_VAR_in_input2218); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_VAR.add(name);


                    IN111=(Token)match(input,IN,FOLLOW_IN_in_input2220); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_IN.add(IN111);


                    }
                    break;

            }


            from=(Token)match(input,VAR,FOLLOW_VAR_in_input2226); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_VAR.add(from);


            if ( state.backtracking==0 ) { 
              int inputIndex = ((operator_scope)operator_stack.peek()).numInputs++;
              JsonStreamExpression input = getVariable(from);
              ((operator_scope)operator_stack.peek()).result.setInput(inputIndex, input.getStream());
              
              JsonStreamExpression inputExpression = new JsonStreamExpression(input.getStream(), inputIndex);
              putVariable(name != null ? name : from, inputExpression);
            }

            // /home/arv/workspace/master/meteor/meteor-meteor/src/main/java/eu/stratosphere/meteor/Meteor.g:332:2: ({...}? =>expr= contextAwareExpression[new InputSelection($operator::numInputs - 1)] )?
            int alt60=2;
            int LA60_0 = input.LA(1);

            if ( (LA60_0==DECIMAL||LA60_0==INTEGER||(LA60_0 >= STRING && LA60_0 <= UINT)||LA60_0==VAR||LA60_0==35||LA60_0==39||LA60_0==42||LA60_0==45||LA60_0==58||LA60_0==60||LA60_0==62||LA60_0==65||LA60_0==68||LA60_0==71) && (( (((input_scope)input_stack.peek()).inputProperty = findInputPropertyRelunctantly(((genericOperator_scope)genericOperator_stack.peek()).operatorInfo, input.LT(1))) != null ))) {
                alt60=1;
            }
            else if ( (LA60_0==ID) ) {
                int LA60_5 = input.LA(2);

                if ( (( (((input_scope)input_stack.peek()).inputProperty = findInputPropertyRelunctantly(((genericOperator_scope)genericOperator_stack.peek()).operatorInfo, input.LT(1))) != null )) ) {
                    alt60=1;
                }
            }
            switch (alt60) {
                case 1 :
                    // /home/arv/workspace/master/meteor/meteor-meteor/src/main/java/eu/stratosphere/meteor/Meteor.g:332:2: {...}? =>expr= contextAwareExpression[new InputSelection($operator::numInputs - 1)]
                    {
                    if ( !(( (((input_scope)input_stack.peek()).inputProperty = findInputPropertyRelunctantly(((genericOperator_scope)genericOperator_stack.peek()).operatorInfo, input.LT(1))) != null )) ) {
                        if (state.backtracking>0) {state.failed=true; return retval;}
                        throw new FailedPredicateException(input, "input", " ($input::inputProperty = findInputPropertyRelunctantly($genericOperator::operatorInfo, input.LT(1))) != null ");
                    }

                    if ( state.backtracking==0 ) { this.input.consume(); }

                    pushFollow(FOLLOW_contextAwareExpression_in_input2245);
                    expr=contextAwareExpression(new InputSelection(((operator_scope)operator_stack.peek()).numInputs - 1));

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_contextAwareExpression.add(expr.getTree());

                    if ( state.backtracking==0 ) { ((input_scope)input_stack.peek()).inputProperty.setValue(((operator_scope)operator_stack.peek()).result, ((operator_scope)operator_stack.peek()).numInputs-1, (expr!=null?((EvaluationExpression)expr.tree):null)); }

                    }
                    break;

            }


            // AST REWRITE
            // elements: 
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            if ( state.backtracking==0 ) {

            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

            root_0 = (EvaluationExpression)adaptor.nil();
            // 335:4: ->
            {
                root_0 = null;
            }


            retval.tree = root_0;
            }

            }

            retval.stop = input.LT(-1);


            if ( state.backtracking==0 ) {

            retval.tree = (EvaluationExpression)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }

        catch (RecognitionException e) {
          throw e;
        }

        finally {
        	// do for sure before leaving
            input_stack.pop();
        }
        return retval;
    }
    // $ANTLR end "input"


    public static class arrayInput_return extends ParserRuleReturnScope {
        EvaluationExpression tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "arrayInput"
    // /home/arv/workspace/master/meteor/meteor-meteor/src/main/java/eu/stratosphere/meteor/Meteor.g:337:1: arrayInput : '[' names+= VAR ( ',' names+= VAR )? ']' 'in' from= VAR ->;
    public final MeteorParser.arrayInput_return arrayInput() throws RecognitionException {
        MeteorParser.arrayInput_return retval = new MeteorParser.arrayInput_return();
        retval.start = input.LT(1);


        EvaluationExpression root_0 = null;

        Token from=null;
        Token char_literal112=null;
        Token char_literal113=null;
        Token char_literal114=null;
        Token string_literal115=null;
        Token names=null;
        List list_names=null;

        EvaluationExpression from_tree=null;
        EvaluationExpression char_literal112_tree=null;
        EvaluationExpression char_literal113_tree=null;
        EvaluationExpression char_literal114_tree=null;
        EvaluationExpression string_literal115_tree=null;
        EvaluationExpression names_tree=null;
        RewriteRuleTokenStream stream_59=new RewriteRuleTokenStream(adaptor,"token 59");
        RewriteRuleTokenStream stream_43=new RewriteRuleTokenStream(adaptor,"token 43");
        RewriteRuleTokenStream stream_58=new RewriteRuleTokenStream(adaptor,"token 58");
        RewriteRuleTokenStream stream_VAR=new RewriteRuleTokenStream(adaptor,"token VAR");
        RewriteRuleTokenStream stream_IN=new RewriteRuleTokenStream(adaptor,"token IN");

        try {
            // /home/arv/workspace/master/meteor/meteor-meteor/src/main/java/eu/stratosphere/meteor/Meteor.g:338:3: ( '[' names+= VAR ( ',' names+= VAR )? ']' 'in' from= VAR ->)
            // /home/arv/workspace/master/meteor/meteor-meteor/src/main/java/eu/stratosphere/meteor/Meteor.g:338:5: '[' names+= VAR ( ',' names+= VAR )? ']' 'in' from= VAR
            {
            char_literal112=(Token)match(input,58,FOLLOW_58_in_arrayInput2263); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_58.add(char_literal112);


            names=(Token)match(input,VAR,FOLLOW_VAR_in_arrayInput2267); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_VAR.add(names);

            if (list_names==null) list_names=new ArrayList();
            list_names.add(names);


            // /home/arv/workspace/master/meteor/meteor-meteor/src/main/java/eu/stratosphere/meteor/Meteor.g:338:20: ( ',' names+= VAR )?
            int alt61=2;
            int LA61_0 = input.LA(1);

            if ( (LA61_0==43) ) {
                alt61=1;
            }
            switch (alt61) {
                case 1 :
                    // /home/arv/workspace/master/meteor/meteor-meteor/src/main/java/eu/stratosphere/meteor/Meteor.g:338:21: ',' names+= VAR
                    {
                    char_literal113=(Token)match(input,43,FOLLOW_43_in_arrayInput2270); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_43.add(char_literal113);


                    names=(Token)match(input,VAR,FOLLOW_VAR_in_arrayInput2274); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_VAR.add(names);

                    if (list_names==null) list_names=new ArrayList();
                    list_names.add(names);


                    }
                    break;

            }


            char_literal114=(Token)match(input,59,FOLLOW_59_in_arrayInput2278); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_59.add(char_literal114);


            string_literal115=(Token)match(input,IN,FOLLOW_IN_in_arrayInput2280); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_IN.add(string_literal115);


            from=(Token)match(input,VAR,FOLLOW_VAR_in_arrayInput2284); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_VAR.add(from);


            if ( state.backtracking==0 ) { 
              ((operator_scope)operator_stack.peek()).result.setInput(0, getVariable(from).getStream());
              for(int index = 0; index < list_names.size(); index++) {
            	  putVariable((Token) list_names.get(index), new JsonStreamExpression(null, index)); 
              }
            }

            // AST REWRITE
            // elements: 
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            if ( state.backtracking==0 ) {

            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

            root_0 = (EvaluationExpression)adaptor.nil();
            // 344:3: ->
            {
                root_0 = null;
            }


            retval.tree = root_0;
            }

            }

            retval.stop = input.LT(-1);


            if ( state.backtracking==0 ) {

            retval.tree = (EvaluationExpression)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }

        catch (RecognitionException e) {
          throw e;
        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "arrayInput"

    // $ANTLR start synpred1_Meteor
    public final void synpred1_Meteor_fragment() throws RecognitionException {
        // /home/arv/workspace/master/meteor/meteor-meteor/src/main/java/eu/stratosphere/meteor/Meteor.g:102:5: ( operatorExpression )
        // /home/arv/workspace/master/meteor/meteor-meteor/src/main/java/eu/stratosphere/meteor/Meteor.g:102:6: operatorExpression
        {
        pushFollow(FOLLOW_operatorExpression_in_synpred1_Meteor377);
        operatorExpression();

        state._fsp--;
        if (state.failed) return ;

        }

    }
    // $ANTLR end synpred1_Meteor

    // $ANTLR start synpred2_Meteor
    public final void synpred2_Meteor_fragment() throws RecognitionException {
        // /home/arv/workspace/master/meteor/meteor-meteor/src/main/java/eu/stratosphere/meteor/Meteor.g:106:4: ( orExpression '?' )
        // /home/arv/workspace/master/meteor/meteor-meteor/src/main/java/eu/stratosphere/meteor/Meteor.g:106:5: orExpression '?'
        {
        pushFollow(FOLLOW_orExpression_in_synpred2_Meteor397);
        orExpression();

        state._fsp--;
        if (state.failed) return ;

        match(input,56,FOLLOW_56_in_synpred2_Meteor399); if (state.failed) return ;

        }

    }
    // $ANTLR end synpred2_Meteor

    // $ANTLR start synpred3_Meteor
    public final void synpred3_Meteor_fragment() throws RecognitionException {
        // /home/arv/workspace/master/meteor/meteor-meteor/src/main/java/eu/stratosphere/meteor/Meteor.g:108:4: ( orExpression IF )
        // /home/arv/workspace/master/meteor/meteor-meteor/src/main/java/eu/stratosphere/meteor/Meteor.g:108:5: orExpression IF
        {
        pushFollow(FOLLOW_orExpression_in_synpred3_Meteor438);
        orExpression();

        state._fsp--;
        if (state.failed) return ;

        match(input,IF,FOLLOW_IF_in_synpred3_Meteor440); if (state.failed) return ;

        }

    }
    // $ANTLR end synpred3_Meteor

    // $ANTLR start synpred4_Meteor
    public final void synpred4_Meteor_fragment() throws RecognitionException {
        // /home/arv/workspace/master/meteor/meteor-meteor/src/main/java/eu/stratosphere/meteor/Meteor.g:156:4: ( '(' ID ')' )
        // /home/arv/workspace/master/meteor/meteor-meteor/src/main/java/eu/stratosphere/meteor/Meteor.g:156:5: '(' ID ')'
        {
        match(input,39,FOLLOW_39_in_synpred4_Meteor907); if (state.failed) return ;

        match(input,ID,FOLLOW_ID_in_synpred4_Meteor909); if (state.failed) return ;

        match(input,40,FOLLOW_40_in_synpred4_Meteor911); if (state.failed) return ;

        }

    }
    // $ANTLR end synpred4_Meteor

    // $ANTLR start synpred5_Meteor
    public final void synpred5_Meteor_fragment() throws RecognitionException {
        // /home/arv/workspace/master/meteor/meteor-meteor/src/main/java/eu/stratosphere/meteor/Meteor.g:158:4: ( generalPathExpression AS )
        // /home/arv/workspace/master/meteor/meteor-meteor/src/main/java/eu/stratosphere/meteor/Meteor.g:158:5: generalPathExpression AS
        {
        pushFollow(FOLLOW_generalPathExpression_in_synpred5_Meteor937);
        generalPathExpression();

        state._fsp--;
        if (state.failed) return ;

        match(input,AS,FOLLOW_AS_in_synpred5_Meteor939); if (state.failed) return ;

        }

    }
    // $ANTLR end synpred5_Meteor

    // $ANTLR start synpred6_Meteor
    public final void synpred6_Meteor_fragment() throws RecognitionException {
        // /home/arv/workspace/master/meteor/meteor-meteor/src/main/java/eu/stratosphere/meteor/Meteor.g:164:5: ( pathExpression[EvaluationExpression.VALUE] )
        // /home/arv/workspace/master/meteor/meteor-meteor/src/main/java/eu/stratosphere/meteor/Meteor.g:164:6: pathExpression[EvaluationExpression.VALUE]
        {
        pushFollow(FOLLOW_pathExpression_in_synpred6_Meteor982);
        pathExpression(EvaluationExpression.VALUE);

        state._fsp--;
        if (state.failed) return ;

        }

    }
    // $ANTLR end synpred6_Meteor

    // $ANTLR start synpred7_Meteor
    public final void synpred7_Meteor_fragment() throws RecognitionException {
        // /home/arv/workspace/master/meteor/meteor-meteor/src/main/java/eu/stratosphere/meteor/Meteor.g:172:4: ( pathSegment )
        // /home/arv/workspace/master/meteor/meteor-meteor/src/main/java/eu/stratosphere/meteor/Meteor.g:172:5: pathSegment
        {
        pushFollow(FOLLOW_pathSegment_in_synpred7_Meteor1042);
        pathSegment();

        state._fsp--;
        if (state.failed) return ;

        }

    }
    // $ANTLR end synpred7_Meteor

    // $ANTLR start synpred8_Meteor
    public final void synpred8_Meteor_fragment() throws RecognitionException {
        // /home/arv/workspace/master/meteor/meteor-meteor/src/main/java/eu/stratosphere/meteor/Meteor.g:179:5: ( '?.' )
        // /home/arv/workspace/master/meteor/meteor-meteor/src/main/java/eu/stratosphere/meteor/Meteor.g:179:6: '?.'
        {
        match(input,57,FOLLOW_57_in_synpred8_Meteor1091); if (state.failed) return ;

        }

    }
    // $ANTLR end synpred8_Meteor

    // $ANTLR start synpred9_Meteor
    public final void synpred9_Meteor_fragment() throws RecognitionException {
        // /home/arv/workspace/master/meteor/meteor-meteor/src/main/java/eu/stratosphere/meteor/Meteor.g:180:5: ( '.' )
        // /home/arv/workspace/master/meteor/meteor-meteor/src/main/java/eu/stratosphere/meteor/Meteor.g:180:6: '.'
        {
        match(input,46,FOLLOW_46_in_synpred9_Meteor1121); if (state.failed) return ;

        }

    }
    // $ANTLR end synpred9_Meteor

    // $ANTLR start synpred10_Meteor
    public final void synpred10_Meteor_fragment() throws RecognitionException {
        // /home/arv/workspace/master/meteor/meteor-meteor/src/main/java/eu/stratosphere/meteor/Meteor.g:181:5: ( '[' )
        // /home/arv/workspace/master/meteor/meteor-meteor/src/main/java/eu/stratosphere/meteor/Meteor.g:181:6: '['
        {
        match(input,58,FOLLOW_58_in_synpred10_Meteor1150); if (state.failed) return ;

        }

    }
    // $ANTLR end synpred10_Meteor

    // $ANTLR start synpred11_Meteor
    public final void synpred11_Meteor_fragment() throws RecognitionException {
        // /home/arv/workspace/master/meteor/meteor-meteor/src/main/java/eu/stratosphere/meteor/Meteor.g:193:4: ( ID '(' )
        // /home/arv/workspace/master/meteor/meteor-meteor/src/main/java/eu/stratosphere/meteor/Meteor.g:193:5: ID '('
        {
        match(input,ID,FOLLOW_ID_in_synpred11_Meteor1281); if (state.failed) return ;

        match(input,39,FOLLOW_39_in_synpred11_Meteor1283); if (state.failed) return ;

        }

    }
    // $ANTLR end synpred11_Meteor

    // $ANTLR start synpred12_Meteor
    public final void synpred12_Meteor_fragment() throws RecognitionException {
        // /home/arv/workspace/master/meteor/meteor-meteor/src/main/java/eu/stratosphere/meteor/Meteor.g:196:4: ( VAR '[' VAR )
        // /home/arv/workspace/master/meteor/meteor-meteor/src/main/java/eu/stratosphere/meteor/Meteor.g:196:5: VAR '[' VAR
        {
        match(input,VAR,FOLLOW_VAR_in_synpred12_Meteor1306); if (state.failed) return ;

        match(input,58,FOLLOW_58_in_synpred12_Meteor1308); if (state.failed) return ;

        match(input,VAR,FOLLOW_VAR_in_synpred12_Meteor1310); if (state.failed) return ;

        }

    }
    // $ANTLR end synpred12_Meteor

    // $ANTLR start synpred13_Meteor
    public final void synpred13_Meteor_fragment() throws RecognitionException {
        // /home/arv/workspace/master/meteor/meteor-meteor/src/main/java/eu/stratosphere/meteor/Meteor.g:198:6: ( ID ':' )
        // /home/arv/workspace/master/meteor/meteor-meteor/src/main/java/eu/stratosphere/meteor/Meteor.g:198:7: ID ':'
        {
        match(input,ID,FOLLOW_ID_in_synpred13_Meteor1331); if (state.failed) return ;

        match(input,48,FOLLOW_48_in_synpred13_Meteor1333); if (state.failed) return ;

        }

    }
    // $ANTLR end synpred13_Meteor

    // $ANTLR start synpred14_Meteor
    public final void synpred14_Meteor_fragment() throws RecognitionException {
        // /home/arv/workspace/master/meteor/meteor-meteor/src/main/java/eu/stratosphere/meteor/Meteor.g:224:5: ( ID ':' )
        // /home/arv/workspace/master/meteor/meteor-meteor/src/main/java/eu/stratosphere/meteor/Meteor.g:224:6: ID ':'
        {
        match(input,ID,FOLLOW_ID_in_synpred14_Meteor1572); if (state.failed) return ;

        match(input,48,FOLLOW_48_in_synpred14_Meteor1574); if (state.failed) return ;

        }

    }
    // $ANTLR end synpred14_Meteor

    // $ANTLR start synpred15_Meteor
    public final void synpred15_Meteor_fragment() throws RecognitionException {
        // /home/arv/workspace/master/meteor/meteor-meteor/src/main/java/eu/stratosphere/meteor/Meteor.g:300:2: ( '[' )
        // /home/arv/workspace/master/meteor/meteor-meteor/src/main/java/eu/stratosphere/meteor/Meteor.g:300:3: '['
        {
        match(input,58,FOLLOW_58_in_synpred15_Meteor2115); if (state.failed) return ;

        }

    }
    // $ANTLR end synpred15_Meteor

    // $ANTLR start synpred16_Meteor
    public final void synpred16_Meteor_fragment() throws RecognitionException {
        // /home/arv/workspace/master/meteor/meteor-meteor/src/main/java/eu/stratosphere/meteor/Meteor.g:300:23: ( VAR )
        // /home/arv/workspace/master/meteor/meteor-meteor/src/main/java/eu/stratosphere/meteor/Meteor.g:300:24: VAR
        {
        match(input,VAR,FOLLOW_VAR_in_synpred16_Meteor2124); if (state.failed) return ;

        }

    }
    // $ANTLR end synpred16_Meteor

    // $ANTLR start synpred17_Meteor
    public final void synpred17_Meteor_fragment() throws RecognitionException {
        // /home/arv/workspace/master/meteor/meteor-meteor/src/main/java/eu/stratosphere/meteor/Meteor.g:300:38: ( ',' )
        // /home/arv/workspace/master/meteor/meteor-meteor/src/main/java/eu/stratosphere/meteor/Meteor.g:300:39: ','
        {
        match(input,43,FOLLOW_43_in_synpred17_Meteor2132); if (state.failed) return ;

        }

    }
    // $ANTLR end synpred17_Meteor

    // Delegated rules

    public final boolean synpred14_Meteor() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred14_Meteor_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred17_Meteor() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred17_Meteor_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred2_Meteor() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred2_Meteor_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred8_Meteor() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred8_Meteor_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred13_Meteor() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred13_Meteor_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred6_Meteor() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred6_Meteor_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred15_Meteor() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred15_Meteor_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred1_Meteor() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred1_Meteor_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred3_Meteor() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred3_Meteor_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred5_Meteor() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred5_Meteor_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred10_Meteor() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred10_Meteor_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred12_Meteor() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred12_Meteor_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred11_Meteor() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred11_Meteor_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred4_Meteor() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred4_Meteor_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred7_Meteor() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred7_Meteor_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred9_Meteor() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred9_Meteor_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred16_Meteor() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred16_Meteor_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }


 

    public static final BitSet FOLLOW_statement_in_script121 = new BitSet(new long[]{0x0002000000000000L});
    public static final BitSet FOLLOW_49_in_script123 = new BitSet(new long[]{0x8000000200010002L,0x000000000000000CL});
    public static final BitSet FOLLOW_assignment_in_statement137 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_operator_in_statement141 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_packageImport_in_statement145 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_functionDefinition_in_statement149 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_javaudf_in_statement153 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_66_in_packageImport168 = new BitSet(new long[]{0x0000000000010000L});
    public static final BitSet FOLLOW_ID_in_packageImport172 = new BitSet(new long[]{0x0000080000000002L});
    public static final BitSet FOLLOW_43_in_packageImport183 = new BitSet(new long[]{0x0000000000010000L});
    public static final BitSet FOLLOW_ID_in_packageImport187 = new BitSet(new long[]{0x0000080000000002L});
    public static final BitSet FOLLOW_VAR_in_assignment204 = new BitSet(new long[]{0x0010000000000000L});
    public static final BitSet FOLLOW_52_in_assignment206 = new BitSet(new long[]{0x8000000000010000L,0x0000000000000008L});
    public static final BitSet FOLLOW_operator_in_assignment210 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ID_in_functionDefinition227 = new BitSet(new long[]{0x0010000000000000L});
    public static final BitSet FOLLOW_52_in_functionDefinition229 = new BitSet(new long[]{0x0000000000004000L});
    public static final BitSet FOLLOW_inlineFunction_in_functionDefinition233 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_FN_in_inlineFunction259 = new BitSet(new long[]{0x0000008000000000L});
    public static final BitSet FOLLOW_39_in_inlineFunction261 = new BitSet(new long[]{0x0000010000010000L});
    public static final BitSet FOLLOW_ID_in_inlineFunction270 = new BitSet(new long[]{0x0000090000000000L});
    public static final BitSet FOLLOW_43_in_inlineFunction277 = new BitSet(new long[]{0x0000000000010000L});
    public static final BitSet FOLLOW_ID_in_inlineFunction281 = new BitSet(new long[]{0x0000090000000000L});
    public static final BitSet FOLLOW_40_in_inlineFunction292 = new BitSet(new long[]{0x5400248A60090100L,0x0000000000000092L});
    public static final BitSet FOLLOW_contextAwareExpression_in_inlineFunction304 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ID_in_javaudf326 = new BitSet(new long[]{0x0010000000000000L});
    public static final BitSet FOLLOW_52_in_javaudf328 = new BitSet(new long[]{0x0000000000100000L});
    public static final BitSet FOLLOW_JAVAUDF_in_javaudf330 = new BitSet(new long[]{0x0000008000000000L});
    public static final BitSet FOLLOW_39_in_javaudf332 = new BitSet(new long[]{0x0000000020000000L});
    public static final BitSet FOLLOW_STRING_in_javaudf336 = new BitSet(new long[]{0x0000010000000000L});
    public static final BitSet FOLLOW_40_in_javaudf338 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ternaryExpression_in_contextAwareExpression366 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_operatorExpression_in_expression381 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ternaryExpression_in_expression387 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_orExpression_in_ternaryExpression405 = new BitSet(new long[]{0x0100000000000000L});
    public static final BitSet FOLLOW_56_in_ternaryExpression407 = new BitSet(new long[]{0x5400248A60090100L,0x0000000000000092L});
    public static final BitSet FOLLOW_orExpression_in_ternaryExpression411 = new BitSet(new long[]{0x0001000000000000L});
    public static final BitSet FOLLOW_48_in_ternaryExpression413 = new BitSet(new long[]{0x5400248A60090100L,0x0000000000000092L});
    public static final BitSet FOLLOW_orExpression_in_ternaryExpression417 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_orExpression_in_ternaryExpression446 = new BitSet(new long[]{0x0000000000020000L});
    public static final BitSet FOLLOW_IF_in_ternaryExpression448 = new BitSet(new long[]{0x5400248A60090100L,0x0000000000000092L});
    public static final BitSet FOLLOW_orExpression_in_ternaryExpression452 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_orExpression_in_ternaryExpression475 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_andExpression_in_orExpression488 = new BitSet(new long[]{0x0000000002000002L,0x0000000000000020L});
    public static final BitSet FOLLOW_OR_in_orExpression492 = new BitSet(new long[]{0x5400248A60090100L,0x0000000000000092L});
    public static final BitSet FOLLOW_69_in_orExpression496 = new BitSet(new long[]{0x5400248A60090100L,0x0000000000000092L});
    public static final BitSet FOLLOW_andExpression_in_orExpression501 = new BitSet(new long[]{0x0000000002000002L,0x0000000000000020L});
    public static final BitSet FOLLOW_elementExpression_in_andExpression530 = new BitSet(new long[]{0x0000002000000012L});
    public static final BitSet FOLLOW_AND_in_andExpression534 = new BitSet(new long[]{0x5400248A60090100L,0x0000000000000092L});
    public static final BitSet FOLLOW_37_in_andExpression538 = new BitSet(new long[]{0x5400248A60090100L,0x0000000000000092L});
    public static final BitSet FOLLOW_elementExpression_in_andExpression543 = new BitSet(new long[]{0x0000002000000012L});
    public static final BitSet FOLLOW_comparisonExpression_in_elementExpression572 = new BitSet(new long[]{0x0000000000440002L});
    public static final BitSet FOLLOW_NOT_in_elementExpression577 = new BitSet(new long[]{0x0000000000040000L});
    public static final BitSet FOLLOW_IN_in_elementExpression580 = new BitSet(new long[]{0x5400248A60090100L,0x0000000000000092L});
    public static final BitSet FOLLOW_comparisonExpression_in_elementExpression584 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_arithmeticExpression_in_comparisonExpression625 = new BitSet(new long[]{0x00EC001000000002L});
    public static final BitSet FOLLOW_51_in_comparisonExpression631 = new BitSet(new long[]{0x5400248A60090100L,0x0000000000000092L});
    public static final BitSet FOLLOW_55_in_comparisonExpression637 = new BitSet(new long[]{0x5400248A60090100L,0x0000000000000092L});
    public static final BitSet FOLLOW_50_in_comparisonExpression643 = new BitSet(new long[]{0x5400248A60090100L,0x0000000000000092L});
    public static final BitSet FOLLOW_54_in_comparisonExpression649 = new BitSet(new long[]{0x5400248A60090100L,0x0000000000000092L});
    public static final BitSet FOLLOW_53_in_comparisonExpression655 = new BitSet(new long[]{0x5400248A60090100L,0x0000000000000092L});
    public static final BitSet FOLLOW_36_in_comparisonExpression661 = new BitSet(new long[]{0x5400248A60090100L,0x0000000000000092L});
    public static final BitSet FOLLOW_arithmeticExpression_in_comparisonExpression666 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_multiplicationExpression_in_arithmeticExpression746 = new BitSet(new long[]{0x0000120000000002L});
    public static final BitSet FOLLOW_41_in_arithmeticExpression752 = new BitSet(new long[]{0x5400248A60090100L,0x0000000000000092L});
    public static final BitSet FOLLOW_44_in_arithmeticExpression758 = new BitSet(new long[]{0x5400248A60090100L,0x0000000000000092L});
    public static final BitSet FOLLOW_multiplicationExpression_in_arithmeticExpression763 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_preincrementExpression_in_multiplicationExpression806 = new BitSet(new long[]{0x0000800010000002L});
    public static final BitSet FOLLOW_STAR_in_multiplicationExpression812 = new BitSet(new long[]{0x5400248A60090100L,0x0000000000000092L});
    public static final BitSet FOLLOW_47_in_multiplicationExpression818 = new BitSet(new long[]{0x5400248A60090100L,0x0000000000000092L});
    public static final BitSet FOLLOW_preincrementExpression_in_multiplicationExpression823 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_42_in_preincrementExpression864 = new BitSet(new long[]{0x5400248A60090100L,0x0000000000000092L});
    public static final BitSet FOLLOW_preincrementExpression_in_preincrementExpression866 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_45_in_preincrementExpression871 = new BitSet(new long[]{0x5400248A60090100L,0x0000000000000092L});
    public static final BitSet FOLLOW_preincrementExpression_in_preincrementExpression873 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_unaryExpression_in_preincrementExpression878 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_castExpression_in_unaryExpression897 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_39_in_castExpression915 = new BitSet(new long[]{0x0000000000010000L});
    public static final BitSet FOLLOW_ID_in_castExpression919 = new BitSet(new long[]{0x0000010000000000L});
    public static final BitSet FOLLOW_40_in_castExpression921 = new BitSet(new long[]{0x5400008260090100L,0x0000000000000012L});
    public static final BitSet FOLLOW_generalPathExpression_in_castExpression925 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_generalPathExpression_in_castExpression945 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_AS_in_castExpression947 = new BitSet(new long[]{0x0000000000010000L});
    public static final BitSet FOLLOW_ID_in_castExpression951 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_generalPathExpression_in_castExpression962 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_valueExpression_in_generalPathExpression974 = new BitSet(new long[]{0x0600400000000002L});
    public static final BitSet FOLLOW_pathExpression_in_generalPathExpression989 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_pathExpression_in_contextAwarePathExpression1018 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_pathSegment_in_pathExpression1034 = new BitSet(new long[]{0x0600400000000002L});
    public static final BitSet FOLLOW_pathExpression_in_pathExpression1048 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_57_in_pathSegment1095 = new BitSet(new long[]{0x0000000000010000L});
    public static final BitSet FOLLOW_ID_in_pathSegment1099 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_46_in_pathSegment1126 = new BitSet(new long[]{0x0000000000010000L});
    public static final BitSet FOLLOW_ID_in_pathSegment1130 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_arrayAccess_in_pathSegment1155 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_58_in_arrayAccess1166 = new BitSet(new long[]{0x0000000010000000L});
    public static final BitSet FOLLOW_STAR_in_arrayAccess1168 = new BitSet(new long[]{0x0800000000000000L});
    public static final BitSet FOLLOW_59_in_arrayAccess1170 = new BitSet(new long[]{0x0600400000000000L});
    public static final BitSet FOLLOW_pathExpression_in_arrayAccess1174 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_58_in_arrayAccess1195 = new BitSet(new long[]{0x0000000040080000L});
    public static final BitSet FOLLOW_INTEGER_in_arrayAccess1200 = new BitSet(new long[]{0x0800000000000000L});
    public static final BitSet FOLLOW_UINT_in_arrayAccess1206 = new BitSet(new long[]{0x0800000000000000L});
    public static final BitSet FOLLOW_59_in_arrayAccess1209 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_58_in_arrayAccess1227 = new BitSet(new long[]{0x0000000040080000L});
    public static final BitSet FOLLOW_INTEGER_in_arrayAccess1232 = new BitSet(new long[]{0x0001000000000000L});
    public static final BitSet FOLLOW_UINT_in_arrayAccess1238 = new BitSet(new long[]{0x0001000000000000L});
    public static final BitSet FOLLOW_48_in_arrayAccess1241 = new BitSet(new long[]{0x0000000040080000L});
    public static final BitSet FOLLOW_INTEGER_in_arrayAccess1246 = new BitSet(new long[]{0x0800000000000000L});
    public static final BitSet FOLLOW_UINT_in_arrayAccess1252 = new BitSet(new long[]{0x0800000000000000L});
    public static final BitSet FOLLOW_59_in_arrayAccess1255 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_methodCall_in_valueExpression1287 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_parenthesesExpression_in_valueExpression1293 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_literal_in_valueExpression1299 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_streamIndexAccess_in_valueExpression1314 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_VAR_in_valueExpression1319 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ID_in_valueExpression1339 = new BitSet(new long[]{0x0001000000000000L});
    public static final BitSet FOLLOW_48_in_valueExpression1341 = new BitSet(new long[]{0x0000000000010000L});
    public static final BitSet FOLLOW_ID_in_valueExpression1347 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_arrayCreation_in_valueExpression1367 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_objectCreation_in_valueExpression1373 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_operator_in_operatorExpression1386 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_39_in_parenthesesExpression1407 = new BitSet(new long[]{0xD400248A60090100L,0x000000000000009AL});
    public static final BitSet FOLLOW_expression_in_parenthesesExpression1409 = new BitSet(new long[]{0x0000010000000000L});
    public static final BitSet FOLLOW_40_in_parenthesesExpression1411 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ID_in_methodCall1440 = new BitSet(new long[]{0x0001000000000000L});
    public static final BitSet FOLLOW_48_in_methodCall1442 = new BitSet(new long[]{0x0000000000010000L});
    public static final BitSet FOLLOW_ID_in_methodCall1448 = new BitSet(new long[]{0x0000008000000000L});
    public static final BitSet FOLLOW_39_in_methodCall1450 = new BitSet(new long[]{0xD40025CA60094100L,0x000000000000009AL});
    public static final BitSet FOLLOW_expression_in_methodCall1458 = new BitSet(new long[]{0x0000090000000000L});
    public static final BitSet FOLLOW_lowerOrderFunction_in_methodCall1466 = new BitSet(new long[]{0x0000090000000000L});
    public static final BitSet FOLLOW_43_in_methodCall1474 = new BitSet(new long[]{0xD40024CA60094100L,0x000000000000009AL});
    public static final BitSet FOLLOW_expression_in_methodCall1479 = new BitSet(new long[]{0x0000090000000000L});
    public static final BitSet FOLLOW_lowerOrderFunction_in_methodCall1487 = new BitSet(new long[]{0x0000090000000000L});
    public static final BitSet FOLLOW_40_in_methodCall1498 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_38_in_lowerOrderFunction1513 = new BitSet(new long[]{0x0000000000010000L});
    public static final BitSet FOLLOW_ID_in_lowerOrderFunction1518 = new BitSet(new long[]{0x0001000000000000L});
    public static final BitSet FOLLOW_48_in_lowerOrderFunction1520 = new BitSet(new long[]{0x0000000000010000L});
    public static final BitSet FOLLOW_ID_in_lowerOrderFunction1526 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_inlineFunction_in_lowerOrderFunction1552 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ID_in_fieldAssignment1578 = new BitSet(new long[]{0x0001000000000000L});
    public static final BitSet FOLLOW_48_in_fieldAssignment1580 = new BitSet(new long[]{0xD400248A60090100L,0x000000000000009AL});
    public static final BitSet FOLLOW_expression_in_fieldAssignment1582 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_VAR_in_fieldAssignment1599 = new BitSet(new long[]{0x0610400000000000L});
    public static final BitSet FOLLOW_46_in_fieldAssignment1608 = new BitSet(new long[]{0x0000000010000000L});
    public static final BitSet FOLLOW_STAR_in_fieldAssignment1610 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_52_in_fieldAssignment1624 = new BitSet(new long[]{0x8000000000010000L,0x0000000000000008L});
    public static final BitSet FOLLOW_operator_in_fieldAssignment1628 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_contextAwarePathExpression_in_fieldAssignment1643 = new BitSet(new long[]{0x0001000000000002L});
    public static final BitSet FOLLOW_48_in_fieldAssignment1654 = new BitSet(new long[]{0xD400248A60090100L,0x000000000000009AL});
    public static final BitSet FOLLOW_expression_in_fieldAssignment1658 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_68_in_objectCreation1723 = new BitSet(new long[]{0x0000000200010000L,0x0000000000000040L});
    public static final BitSet FOLLOW_fieldAssignment_in_objectCreation1726 = new BitSet(new long[]{0x0000080000000000L,0x0000000000000040L});
    public static final BitSet FOLLOW_43_in_objectCreation1729 = new BitSet(new long[]{0x0000000200010000L});
    public static final BitSet FOLLOW_fieldAssignment_in_objectCreation1731 = new BitSet(new long[]{0x0000080000000000L,0x0000000000000040L});
    public static final BitSet FOLLOW_43_in_objectCreation1735 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000040L});
    public static final BitSet FOLLOW_70_in_objectCreation1740 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_65_in_literal1778 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_60_in_literal1794 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_DECIMAL_in_literal1810 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_STRING_in_literal1826 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_UINT_in_literal1844 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_INTEGER_in_literal1850 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_62_in_literal1866 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_VAR_in_streamIndexAccess1882 = new BitSet(new long[]{0x0400000000000000L});
    public static final BitSet FOLLOW_58_in_streamIndexAccess1891 = new BitSet(new long[]{0x5400008260090100L,0x0000000000000012L});
    public static final BitSet FOLLOW_generalPathExpression_in_streamIndexAccess1895 = new BitSet(new long[]{0x0800000000000000L});
    public static final BitSet FOLLOW_59_in_streamIndexAccess1897 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_58_in_arrayCreation1926 = new BitSet(new long[]{0xD400248A60090100L,0x000000000000009AL});
    public static final BitSet FOLLOW_expression_in_arrayCreation1930 = new BitSet(new long[]{0x0800080000000000L});
    public static final BitSet FOLLOW_43_in_arrayCreation1933 = new BitSet(new long[]{0xD400248A60090100L,0x000000000000009AL});
    public static final BitSet FOLLOW_expression_in_arrayCreation1937 = new BitSet(new long[]{0x0800080000000000L});
    public static final BitSet FOLLOW_43_in_arrayCreation1941 = new BitSet(new long[]{0x0800000000000000L});
    public static final BitSet FOLLOW_59_in_arrayCreation1944 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_readOperator_in_operator1981 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_writeOperator_in_operator1985 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_genericOperator_in_operator1989 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_63_in_readOperator2003 = new BitSet(new long[]{0x2000000000000000L});
    public static final BitSet FOLLOW_61_in_readOperator2005 = new BitSet(new long[]{0x0000000020010000L});
    public static final BitSet FOLLOW_ID_in_readOperator2010 = new BitSet(new long[]{0x0000000020000000L});
    public static final BitSet FOLLOW_STRING_in_readOperator2015 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ID_in_readOperator2021 = new BitSet(new long[]{0x0000008000000000L});
    public static final BitSet FOLLOW_39_in_readOperator2023 = new BitSet(new long[]{0x0000000020000000L});
    public static final BitSet FOLLOW_STRING_in_readOperator2027 = new BitSet(new long[]{0x0000010000000000L});
    public static final BitSet FOLLOW_40_in_readOperator2029 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_67_in_writeOperator2043 = new BitSet(new long[]{0x0000000200000000L});
    public static final BitSet FOLLOW_VAR_in_writeOperator2047 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000001L});
    public static final BitSet FOLLOW_64_in_writeOperator2049 = new BitSet(new long[]{0x0000000020010000L});
    public static final BitSet FOLLOW_ID_in_writeOperator2054 = new BitSet(new long[]{0x0000000020000000L});
    public static final BitSet FOLLOW_STRING_in_writeOperator2059 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ID_in_writeOperator2065 = new BitSet(new long[]{0x0000008000000000L});
    public static final BitSet FOLLOW_39_in_writeOperator2067 = new BitSet(new long[]{0x0000000020000000L});
    public static final BitSet FOLLOW_STRING_in_writeOperator2071 = new BitSet(new long[]{0x0000010000000000L});
    public static final BitSet FOLLOW_40_in_writeOperator2073 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ID_in_genericOperator2094 = new BitSet(new long[]{0x0001000000000000L});
    public static final BitSet FOLLOW_48_in_genericOperator2096 = new BitSet(new long[]{0x0000000000010000L});
    public static final BitSet FOLLOW_ID_in_genericOperator2102 = new BitSet(new long[]{0x0400000200010000L});
    public static final BitSet FOLLOW_operatorFlag_in_genericOperator2110 = new BitSet(new long[]{0x0400000200010000L});
    public static final BitSet FOLLOW_arrayInput_in_genericOperator2119 = new BitSet(new long[]{0x0000000000010002L});
    public static final BitSet FOLLOW_input_in_genericOperator2128 = new BitSet(new long[]{0x0000080000010002L});
    public static final BitSet FOLLOW_43_in_genericOperator2136 = new BitSet(new long[]{0x0000000200000000L});
    public static final BitSet FOLLOW_input_in_genericOperator2138 = new BitSet(new long[]{0x0000080000010002L});
    public static final BitSet FOLLOW_operatorOption_in_genericOperator2143 = new BitSet(new long[]{0x0000000000010002L});
    public static final BitSet FOLLOW_ID_in_operatorOption2165 = new BitSet(new long[]{0x5400248A60090100L,0x0000000000000092L});
    public static final BitSet FOLLOW_contextAwareExpression_in_operatorOption2174 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ID_in_operatorFlag2195 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_VAR_in_input2218 = new BitSet(new long[]{0x0000000000040000L});
    public static final BitSet FOLLOW_IN_in_input2220 = new BitSet(new long[]{0x0000000200000000L});
    public static final BitSet FOLLOW_VAR_in_input2226 = new BitSet(new long[]{0x5400248A60090102L,0x0000000000000092L});
    public static final BitSet FOLLOW_contextAwareExpression_in_input2245 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_58_in_arrayInput2263 = new BitSet(new long[]{0x0000000200000000L});
    public static final BitSet FOLLOW_VAR_in_arrayInput2267 = new BitSet(new long[]{0x0800080000000000L});
    public static final BitSet FOLLOW_43_in_arrayInput2270 = new BitSet(new long[]{0x0000000200000000L});
    public static final BitSet FOLLOW_VAR_in_arrayInput2274 = new BitSet(new long[]{0x0800000000000000L});
    public static final BitSet FOLLOW_59_in_arrayInput2278 = new BitSet(new long[]{0x0000000000040000L});
    public static final BitSet FOLLOW_IN_in_arrayInput2280 = new BitSet(new long[]{0x0000000200000000L});
    public static final BitSet FOLLOW_VAR_in_arrayInput2284 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_operatorExpression_in_synpred1_Meteor377 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_orExpression_in_synpred2_Meteor397 = new BitSet(new long[]{0x0100000000000000L});
    public static final BitSet FOLLOW_56_in_synpred2_Meteor399 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_orExpression_in_synpred3_Meteor438 = new BitSet(new long[]{0x0000000000020000L});
    public static final BitSet FOLLOW_IF_in_synpred3_Meteor440 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_39_in_synpred4_Meteor907 = new BitSet(new long[]{0x0000000000010000L});
    public static final BitSet FOLLOW_ID_in_synpred4_Meteor909 = new BitSet(new long[]{0x0000010000000000L});
    public static final BitSet FOLLOW_40_in_synpred4_Meteor911 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_generalPathExpression_in_synpred5_Meteor937 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_AS_in_synpred5_Meteor939 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_pathExpression_in_synpred6_Meteor982 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_pathSegment_in_synpred7_Meteor1042 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_57_in_synpred8_Meteor1091 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_46_in_synpred9_Meteor1121 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_58_in_synpred10_Meteor1150 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ID_in_synpred11_Meteor1281 = new BitSet(new long[]{0x0000008000000000L});
    public static final BitSet FOLLOW_39_in_synpred11_Meteor1283 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_VAR_in_synpred12_Meteor1306 = new BitSet(new long[]{0x0400000000000000L});
    public static final BitSet FOLLOW_58_in_synpred12_Meteor1308 = new BitSet(new long[]{0x0000000200000000L});
    public static final BitSet FOLLOW_VAR_in_synpred12_Meteor1310 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ID_in_synpred13_Meteor1331 = new BitSet(new long[]{0x0001000000000000L});
    public static final BitSet FOLLOW_48_in_synpred13_Meteor1333 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ID_in_synpred14_Meteor1572 = new BitSet(new long[]{0x0001000000000000L});
    public static final BitSet FOLLOW_48_in_synpred14_Meteor1574 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_58_in_synpred15_Meteor2115 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_VAR_in_synpred16_Meteor2124 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_43_in_synpred17_Meteor2132 = new BitSet(new long[]{0x0000000000000002L});

}