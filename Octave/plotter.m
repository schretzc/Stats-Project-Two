%references in the code
%https://stackoverflow.com/questions/25904643/plotting-function-in-octave
%https://www.youtube.com/playlist?list=PL1A2CSdiySGJ6oZe6XB-TTCFuHc5Fs1PO
%used those to get familiar with the syntax. used the whole youtube playlist
%https://docs.octave.org/latest/Defining-Functions.html
%used to figure out common function syntaxes
%https://docs.octave.org/v6.4.0/Random-Number-Generation.html
%to figure out random numbers for salting
%https://docs.octave.org/latest/The-for-Statement.html
%for for loops
%https://docs.octave.org/latest/Cell-Arrays.html
%for array work
lowerBound = -100
upperBound = 100
x = linspace(lowerBound,upperBound);

function y = plotter(x)
  y = x.^2 + 13;
endfunction

yValues = plotter(x);
%plot(x, yValues);

randomY = yValues
function y=  salty(y)
  for i = 1:length(y)
        y(i) = y(i) + randi([-1000, 1000]);
  end
end

randomY = salty(randomY);
plot(x, randomY);






