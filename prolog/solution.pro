% The "left of" predicate
left([fleft |_], [left  |_]).
left([left  |_], [center|_]).
left([center|_], [right |_]).
left([right |_], [fright|_]).

left(A, B, L) :-
  member(A, L),
  member(B, L),
  left(A, B).

% The "next to" predicate
next(A, B, L) :- left(A, B, L).
next(A, B, L) :- left(B, A, L).

% "G" is a list of attribute lists for each guest, each in form:
% [Location, Name, Color, Drink, citY, Trinket]
riddle(G) :-
  % 0. Structural constraint:
  % The number of guests should be 5
  length(G, 5),

  % 1. Constraints, as corresponding to the text
  %  (following verbatim in comments):

  % Madam Natsiou wore a jaunty blue hat
  member([_, natsiou, blue, _, _, _], G),
  % Lady Winslow was at the far left next to the guest wearing a red jacket.
  next([fleft, winslow, _, _, _, _], [_, _, red, _, _, _], G),
  % The lady in white sat left of someone in green.
  left([_, _, white, _, _, _], [_, _, green, _, _, _], G),
  % I remember that white outfit because the woman spilled her beer all over it.
  member([_, _, white, beer, _, _], G),
  % The traveler from Fraeport was dressed entirely in purple
  member([_, _, purple, _, fraeport, _], G),
  % When one of the dinner guests bragged about her Bird Pendant
  % the woman next to her said they were finer in Fraeport, where she lived.
  next([_, _, _, _, _, pendant], [_, _, _, _, fraeport, _], G),

  % So Doctor Marcolla showed off a prized Snuff Tin,
  member([_, marcolla, _, _, _, tin], G),
  % at which the lady from Dunwall scoffed, saying it was no match for her Ring.
  member([_, _, _, _, dunwall, ring], G),
  % Someone else carried a valuable War Medal and when she saw it,
  % the visitor from Baleton next to her
  next([_, _, _, _, _, medal], [_, _, _, _, baleton, _], G),
  % almost spilled her neighor's absinthe.
  next([_, _, _, absinthe, _, _], [_, _, _, _, baleton, _], G),
  % Countess Contee raised her rum in toast.
  member([_, contee, _, rum, _, _], G),
  % The lady from Karnaca, full of wine,
  member([_, _, _, wine, karnaca, _], G),
  % jumped onto the table, falling onto the guest in the center seat,
  % spilling the poor woman's whiskey.
  member([center, _, _, whiskey, _, _], G),
  % Then Baroness Finch captivated them all with a story
  % about her wild youth in Dabokva.
  member([_, finch, _, _, dabokva, _], G),

  % 3. Implicit constraints:
  % Pad with information about unmentioned atoms
  member([left, _, _, _, _, _], G),
  member([right, _, _, _, _, _], G),
  member([fright, _, _, _, _, _], G),
  member([_, _, _, _, _, diamond], G).


:- initialization main.

main :-
  riddle(G),
  format('Solution: ~w\n', [G]),
  halt(0).
