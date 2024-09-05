package br.unb.cic.oberon.interpreter

import java.nio.file.{Files, Paths}

import br.unb.cic.oberon.ir.ast.{IntValue, OberonModule}
import br.unb.cic.oberon.parser.ScalaParser
import org.scalatest.funsuite.AnyFunSuite

class InterpreterVisitorTest extends AnyFunSuite {

  test("Test interpreter on stmt05 program") {
    val module = ScalaParser.parseResource("stmts/stmt05.oberon")
    val interpreter = new Interpreter()
    assert(module.name == "SimpleModule")

    interpreter.setTestEnvironment()
    val result = interpreter.run(module)

    assert(result.lookup("x") == Some(IntValue(625)))
    assert(result.lookup("y") == Some(IntValue(100)))

  }

  ignore("Test eval on factorial module") {
    val module = ScalaParser.parseResource("procedures/procedure03.oberon")
    val interpreter = new Interpreter()
    assert(module.name == "Factorial")

    interpreter.setTestEnvironment()
    val result = interpreter.run(module)

    assert(result.lookup("res").isDefined)
    assert(result.lookup("res") == Some(IntValue(120)))
  }

  test("Testing bee1161SomadeFatoriais") {
    val module = ScalaParser.parseResource("stmts/bee1161_SomadeFatoriais.oberon")
    val interpreter = new Interpreter()
    assert(module.name == "bee1161SomadeFatoriais")

    interpreter.setTestEnvironment()
    val result = interpreter.run(module)

    assert(result.lookup("res").isDefined)
    assert(result.lookup("res") == Some(IntValue(48)))
    assert(result.lookup("res2").isDefined)
    assert(result.lookup("res2") == Some(IntValue(2)))
    assert(result.lookup("res3").isDefined)
    assert(result.lookup("res3") == Some(IntValue(3)))


  }

  test(testName = "Beecrownd test of Weighted Averages 1161"){
    val module = ScalaParser.parseResource("stmts/bee1161_SomadeFatoriais.oberon")

    val coreVisitor = new CoreVisitor()
    val coreModule = coreVisitor.transformModule(module)

    assert(module.name == "bee1079Average")

    coreModule.accept(interpreter)

    assert(evalArraySubscript("r", 0) == IntValue(48))
    assert(evalArraySubscript("r", 1) == IntValue(2))
    assert(evalArraySubscript("r", 2) == IntValue(3))

  }
}
